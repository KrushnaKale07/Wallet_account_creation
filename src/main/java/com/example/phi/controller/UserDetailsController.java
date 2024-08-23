package com.example.phi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.phi.model.UserDetailsModel;
import com.example.phi.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api")
public class UserDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@PostMapping("/register")
	public String registerUser(@RequestBody UserDetailsModel userDetailsModel) {
		logger.info("inside UserDetailsController.registerUser()");
		return userDetailsServiceImpl.registerUser(userDetailsModel);

	}

	@GetMapping("/validate/{id}")
	public boolean validateWalletID(@PathVariable Integer id) {
		logger.info("inside UserDetailsController.validateWalletID()");
		return userDetailsServiceImpl.validateWalletID(id);
	}
}
