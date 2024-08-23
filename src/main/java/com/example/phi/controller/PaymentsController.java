package com.example.phi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.phi.model.PaymentModel;
import com.example.phi.service.PaymentService;
import com.example.phi.service.PaymentServiceImpl;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/transactions")
public class PaymentsController {

	private static final Logger logger = LoggerFactory.getLogger(PaymentsController.class);

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, PaymentModel> hashOperations;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	PaymentServiceImpl paymentServiceImpl;

	@PostMapping("/top-up")
	public String topUpWallet(@RequestBody PaymentModel paymentModel) {
		logger.info("inside PaymentsController.topUpWallet()");
		return paymentServiceImpl.topUpWallet(paymentModel);
	}

	@GetMapping("/balance/{id}")
	public Double checkBalance(@PathVariable int id) {
		logger.info("inside PaymentsController.checkBalance()");
		return paymentServiceImpl.checkBalance(id);
	}

	@PostMapping("/transfer")
	public String transfer(@RequestBody PaymentModel paymentModel) {
		logger.info("inside PaymentsController.transfer()");
		return paymentServiceImpl.transfer(paymentModel);
	}

	@GetMapping("/{key}")
	public PaymentModel getTransaction(@PathVariable("key") String key) {
		logger.info("inside PaymentsController.getTransaction()");
		logger.info("Going to get transaction record with id : [{}] ", key);
		return hashOperations.get("PaymentModel", key);
	}

	@PostMapping(value = "/set-transaction", consumes = "application/json")
	public ResponseEntity<String> setTransation(@RequestBody PaymentModel paymentModel) {
		logger.info("inside PaymentsController.setTransation()");
		paymentServiceImpl.setTransation(paymentModel);
		return new ResponseEntity<String>("Wallet topped up successfully.", HttpStatus.OK);
	}
}
