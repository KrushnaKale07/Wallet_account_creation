package com.example.phi.service;

import org.springframework.http.ResponseEntity;

import com.example.phi.model.PaymentModel;

public interface PaymentService {

	public PaymentModel creareNewPayment(PaymentModel paymentModel);

	void topUpWallet(String walletId, double amount);

	void transfer(String fromWalletId, String toWalletId, double amount);
	
	public String setTransation(PaymentModel paymentModel);

//	List<PaymentModel> findByWalletIdAndTimestampBetween(String walletId, LocalDateTime startDate,
//			LocalDateTime endDate);
}
