package com.example.phi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	UserDetailsModel result = null;

	@PostMapping("/register")
	public UserDetailsModel registerUser(@RequestBody UserDetailsModel userDetailsModel) {
		return userDetailsServiceImpl.registerUser(userDetailsModel);
	}
//	
//	public UserDetailsModel creareNewUser(@RequestBody UserDetailsModel userDetailsModel) {
//		try {
//			result = userDetailsServiceImpl.creareNewUser(userDetailsModel);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

	@PostMapping("/validate/{userWalletID}")
	public UserDetailsModel fetchUserDetails(@RequestBody UserDetailsModel userDetailsModel) {
		try {
			result = userDetailsServiceImpl.fetchUserDetails(userDetailsModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}
