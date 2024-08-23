package com.example.phi.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.phi.service.StatementServiceImpl;

@RestController
@RequestMapping("/create")
public class StatementController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentsController.class);
	
	@Autowired
	StatementServiceImpl statementServiceImpl;
	
	@GetMapping("/statement")
	public String generateStatement(@PathVariable String walletId, @PathVariable String startDate,
			@PathVariable String endDate) {
		logger.info("inside StatementController.generateStatement()");
		try {
			LocalDateTime start = LocalDateTime.parse(startDate);
			LocalDateTime end = LocalDateTime.parse(endDate);
			statementServiceImpl.generateStatementFile(walletId, start, end);
			return "Statement file generated successfully for wallet ID: " + walletId;
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to generate statement file for wallet ID: " + walletId;
		}
	}
}
