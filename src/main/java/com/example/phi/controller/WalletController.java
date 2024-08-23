package com.example.phi.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.phi.model.UserDetailsModel;
import com.example.phi.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

	private static final Logger logger = LoggerFactory.getLogger(WalletController.class);

	@Autowired
	private WalletService walletService;

	@PostMapping("/register")
	public UserDetailsModel registerUser(@RequestBody UserDetailsModel userDetailsModel) {
		logger.info("inside WalletController.registerUser()");
		return walletService.registerUser(userDetailsModel);
	}

	@PostMapping("/top-up")
	public String topUpWallet(@RequestParam String walletId, @RequestParam double amount) {
		logger.info("inside WalletController.topUpWallet()");
		walletService.topUpWallet(walletId, amount);
		return "Wallet topped up successfully.";
	}

	@PostMapping("/transfer")
	public String transfer(@RequestParam String fromWalletId, @RequestParam String toWalletId,
			@RequestParam double amount) {
		logger.info("inside WalletController.transfer()");
		walletService.transfer(fromWalletId, toWalletId, amount);
		return "Transfer completed successfully.";
	}

	@GetMapping("/statement")
	public String generateStatement(@RequestParam String walletId, @RequestParam String startDate,
			@RequestParam String endDate) {
		logger.info("inside WalletController.generateStatement()");
		try {
			LocalDateTime start = LocalDateTime.parse(startDate);
			LocalDateTime end = LocalDateTime.parse(endDate);
			walletService.generateStatementFile(walletId, start, end);
			return "Statement file generated successfully for wallet ID: " + walletId;
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed to generate statement file for wallet ID: " + walletId;
		}
	}
}
