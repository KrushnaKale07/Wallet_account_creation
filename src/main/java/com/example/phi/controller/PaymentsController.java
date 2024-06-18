package com.example.phi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.phi.service.PaymentServiceImpl;

@RestController
@RequestMapping("/transactions")
public class PaymentsController {

	@Autowired
	PaymentServiceImpl paymentServiceImpl;
	
	@PostMapping("/top-up")
	public String topUpWallet(@RequestParam String walletId, @RequestParam double amount) {
		paymentServiceImpl.topUpWallet(walletId, amount);
		return "Wallet topped up successfully.";
	}

	@PostMapping("/transfer")
	public String transfer(@RequestParam String fromWalletId, @RequestParam String toWalletId,
			@RequestParam double amount) {
		paymentServiceImpl.transfer(fromWalletId, toWalletId, amount);
		return "Transfer completed successfully.";
	}
	
	
//	@PostMapping("/create")
//	public PaymentModel creareNewPayment(@RequestBody PaymentModel paymentModel) {
//		PaymentModel result = null;
//		try {
//			result = paymentServiceImpl.creareNewPayment(paymentModel);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;		
//	}
}
