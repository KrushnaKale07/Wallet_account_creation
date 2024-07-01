package com.example.phi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.phi.model.PaymentModel;
import com.example.phi.service.PaymentService;
import com.example.phi.service.PaymentServiceImpl;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/transactions")
public class PaymentsController {

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, PaymentModel> hashOperations;

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/{key}")
	public PaymentModel getTransaction(@PathVariable("key") String key) {

		System.out.println("Going to get transaction record with id : " + key);
		return hashOperations.get("PaymentModel", key);
	}

	@PostMapping(value = "/set-transaction", consumes = "application/json")
	public ResponseEntity<String> setTransation(@RequestBody PaymentModel paymentModel) {
		paymentService.setTransation(paymentModel);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

//	@Autowired
//	PaymentServiceImpl paymentServiceImpl;
//	
//	@PostMapping("/top-up")
//	public String topUpWallet(@RequestParam String walletId, @RequestParam double amount) {
//		paymentServiceImpl.topUpWallet(walletId, amount);
//		return "Wallet topped up successfully.";
//	}
//
//	@PostMapping("/transfer")
//	public String transfer(@RequestParam String fromWalletId, @RequestParam String toWalletId,
//			@RequestParam double amount) {
//		paymentServiceImpl.transfer(fromWalletId, toWalletId, amount);
//		return "Transfer completed successfully.";
//	}
}
