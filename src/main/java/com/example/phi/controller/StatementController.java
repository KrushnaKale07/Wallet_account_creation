package com.example.phi.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.phi.service.StatementServiceImpl;

@RestController
@RequestMapping("/create")
public class StatementController {
	
	@Autowired
	StatementServiceImpl statementServiceImpl;
	
	@GetMapping("/statement")
	public String generateStatement(@RequestParam String walletId, @RequestParam String startDate,
			@RequestParam String endDate) {
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
