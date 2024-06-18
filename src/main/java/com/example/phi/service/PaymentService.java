package com.example.phi.service;

import com.example.phi.model.PaymentModel;

public interface PaymentService {

	public PaymentModel creareNewPayment(PaymentModel paymentModel);

	void topUpWallet(String walletId, double amount);

	void transfer(String fromWalletId, String toWalletId, double amount);

//	List<PaymentModel> findByWalletIdAndTimestampBetween(String walletId, LocalDateTime startDate,
//			LocalDateTime endDate);
}
