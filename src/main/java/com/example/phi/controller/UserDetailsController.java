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
		userDetailsServiceImpl.registerUser(userDetailsModel);
		return "Your user id is = '" + userDetailsModel.getId() + "' and your wallet id is = '"
				+ userDetailsModel.getWalletID() + "'. You can search your registory using your user id.";
	}

	@GetMapping("/validate/{id}")
	public boolean validateWalletID(@PathVariable Integer id) {
		return userDetailsServiceImpl.validateWalletID(id);
	}
}
