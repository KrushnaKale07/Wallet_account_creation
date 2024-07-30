package com.example.phi.service;

import com.example.phi.model.PaymentModel;

public interface PaymentService {

	public String topUpWallet(PaymentModel paymentModel);

	public String transfer(PaymentModel paymentModel);
	
	public String setTransation(PaymentModel paymentModel);

	public Double checkBalance(int accountId);

}
