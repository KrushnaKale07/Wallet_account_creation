package com.example.phi.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.phi.model.PaymentModel;
import com.example.phi.model.UserDetailsModel;
import com.example.phi.repository.PaymentRepository;
import com.example.phi.repository.UserDetailsRepository;

@Service
public class WalletService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

	public UserDetailsModel registerUser(UserDetailsModel userDetailsModel) {
		logger.info("inside WalletService.registerUser()");
		userDetailsModel.setWalletID(UUID.randomUUID().toString());
		return userDetailsRepository.save(userDetailsModel);
	}

	public void topUpWallet(String walletId, double amount) {
		logger.info("inside WalletService.topUpWallet()");
		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setWalletId(walletId);
		paymentModel.setDescription("Top-up");
		paymentModel.setAmount(amount);
		paymentModel.setTimestamp(LocalDateTime.now());
		paymentRepository.save(paymentModel);
	}

	public void transfer(String fromWalletId, String toWalletId, double amount) {
		logger.info("inside WalletService.transfer()");
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

	public void generateStatementFile(String walletId, LocalDateTime startDate, LocalDateTime endDate)
			throws IOException {
		logger.info("inside WalletService.generateStatementFile()");
		List<PaymentModel> transactions = paymentRepository.findByWalletIdAndTimestampBetween(walletId, startDate,
				endDate);
		String fileName = "statement_" + walletId + "_" + startDate + "_" + endDate + ".csv";
		FileWriter fileWriter = new FileWriter(fileName);

		// Writing CSV header
		fileWriter.append("ID,Wallet ID,Description,Amount,Timestamp\n");

		// Writing transaction data
		for (PaymentModel paymentModel : transactions) {

			fileWriter.append(String.valueOf(paymentModel.getId())).append(",")
					.append(paymentModel.getWalletId().toString()).append(",").append(paymentModel.getDescription())
					.append(",").append(String.valueOf(paymentModel.getAmount())).append(",")
					.append(paymentModel.getTimestamp().toString()).append("\n");
		}

		fileWriter.flush();
		fileWriter.close();
	}
}
