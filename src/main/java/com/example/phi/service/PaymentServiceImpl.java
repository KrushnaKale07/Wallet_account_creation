package com.example.phi.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import com.example.phi.model.PaymentModel;
import com.example.phi.model.UserDetailsModel;
import com.example.phi.repository.PaymentRepository;
import com.example.phi.repository.UserDetailsRepository;

import jakarta.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired(required = true)
	PaymentRepository paymentRepository;

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	UserDetailsModel userDetailsModel;

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, PaymentModel> hashOperations;

	@Override
	public String topUpWallet(PaymentModel paymentModel) {
		String reply = "";
		try {
			boolean s = userDetailsServiceImpl.validateWalletID(paymentModel.getToUserId()); // Validation of user id
			if (s == true) {
				paymentModel.setTimestamp(LocalDateTime.now());
				double balance = checkBalance(paymentModel.getToUserId());
				balance = balance + paymentModel.getAmount();
				paymentModel.setBalance(balance);
				paymentRepository.save(paymentModel);

				userDetailsModel = userDetailsRepository.getById(paymentModel.getToUserId());
				userDetailsModel.setBalance(balance);
				userDetailsRepository.save(userDetailsModel);

				reply = "Wallet topped up successfully with RS: " + paymentModel.getAmount()
						+ ". Your current balance is " + paymentModel.getBalance();
			} else {

				reply = "User id does not exist please register yourself first using (link)";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
//				+ "Length of '" + paymentModel.getWalletId() + "' user id is not valid.";
	}

	@Override
	public String transfer(PaymentModel paymentModel) {
		try {
			if (userDetailsServiceImpl.validateWalletID(paymentModel.getFromUserId())
					&& userDetailsServiceImpl.validateWalletID(paymentModel.getToUserId())) { // to verify both wallet
																								// id is valid or not

				double balance = checkBalance(paymentModel.getFromUserId()); // to check ballance is sufficient or not
																				// at
				// sender(fromUserID)
				if (balance > 0 && balance >= paymentModel.getAmountToTransfer()) {

					paymentModel
							.setDescription(paymentModel.getAmountToTransfer() + " rupees succesfully transfer from "
									+ paymentModel.getFromWalletId() + " to " + paymentModel.getToWalletId());
					paymentModel.setTimestamp(LocalDateTime.now());

					if (paymentModel.getId() == paymentModel.getFromUserId()) {
						paymentModel.setId(0);
						paymentModel.setFromWalletId(paymentModel.getFromWalletId());
						paymentModel.setBalance(balance - paymentModel.getAmountToTransfer());
						paymentRepository.save(paymentModel);

//						paymentModel.setId(0);
						paymentModel.setToWalletId(paymentModel.getToWalletId());
						paymentModel.setBalance(
								checkBalance(paymentModel.getToUserId()) + paymentModel.getAmountToTransfer());
						paymentRepository.save(paymentModel);

						userDetailsModel = userDetailsRepository.getById(paymentModel.getFromUserId());
						userDetailsModel.setBalance(balance - paymentModel.getAmountToTransfer());
						userDetailsRepository.save(userDetailsModel);

						userDetailsModel = userDetailsRepository.getById(paymentModel.getToUserId());
						userDetailsModel.setBalance(
								checkBalance(paymentModel.getToUserId()) + paymentModel.getAmountToTransfer());
						userDetailsRepository.save(userDetailsModel);

					} else {
						return "id (user id) should be sender id.";
					}

					return paymentModel.getAmountToTransfer() + " successfully transfer and your remaining balance is "
							+ (balance - paymentModel.getAmountToTransfer()) + ".";
				} else {

					return "You dont have sufficient balance to transfer, please check your account balance and top up it first";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Enter valid id(user id) to transfer money";
	}

	@Override
	public String setTransation(PaymentModel paymentModel) {
		System.out.println("Going to set value of transaction i redis");
		paymentRepository.save(paymentModel);
		hashOperations.put("PaymentModel", paymentModel.getWalletId().toString(), paymentModel);
		return "";
	}

	@Override
	public Double checkBalance(int id) {
		UserDetailsModel userDetailsModel = userDetailsRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found"));
		return userDetailsModel.getBalance();
	}
}