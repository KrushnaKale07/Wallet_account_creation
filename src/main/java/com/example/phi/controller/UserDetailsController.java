package com.example.phi.controller;

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

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@PostMapping("/register")
	public String registerUser(@RequestBody UserDetailsModel userDetailsModel) {
		return userDetailsServiceImpl.registerUser(userDetailsModel);
		 
	}

	@GetMapping("/validate/{id}")
	public boolean validateWalletID(@PathVariable Integer id) {
		return userDetailsServiceImpl.validateWalletID(id);
	}
}
