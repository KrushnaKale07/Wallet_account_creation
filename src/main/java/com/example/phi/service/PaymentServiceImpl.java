package com.example.phi.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.phi.model.PaymentModel;
import com.example.phi.repository.PaymentRepository;

import jakarta.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired(required = true)
	PaymentRepository paymentRepository;
	
	@Resource(name = "redisTemplate")
	private HashOperations<String, String, PaymentModel> hashOperations;


	@Override
	public PaymentModel creareNewPayment(PaymentModel paymentModel) {
		PaymentModel result = null;
		try {
			result = paymentRepository.save(paymentModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void topUpWallet(String walletId, double amount) {
		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setWalletId(walletId);
		paymentModel.setDescription("Top-up");
		paymentModel.setAmount(amount);
		paymentModel.setTimestamp(LocalDateTime.now());
		paymentRepository.save(paymentModel);
	}

	@Override
	public void transfer(String fromWalletId, String toWalletId, double amount) {
		// Debit from sender's wallet
		PaymentModel debitTransaction = new PaymentModel();
		debitTransaction.setWalletId(fromWalletId);
		debitTransaction.setDescription("Transfer to " + toWalletId);
		debitTransaction.setAmount(-amount);
		debitTransaction.setTimestamp(LocalDateTime.now());
		paymentRepository.save(debitTransaction);

		// Credit to receiver's wallet
		PaymentModel creditTransaction = new PaymentModel();
		creditTransaction.setWalletId(toWalletId);
		creditTransaction.setDescription("Transfer from " + fromWalletId);
		creditTransaction.setAmount(amount);
		creditTransaction.setTimestamp(LocalDateTime.now());
		paymentRepository.save(creditTransaction);
	}

	@Override
	public String setTransation(PaymentModel paymentModel) {
		System.out.println("Going to set value of transaction i redis");
		hashOperations.put("PaymentModel", paymentModel.getWalletId(), paymentModel);
		return "";
	}
	
	
}
