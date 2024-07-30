package com.example.phi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.phi.model.UserDetailsModel;
import com.example.phi.repository.UserDetailsRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetailsModel registerUser(UserDetailsModel userDetailsModel) {
		userDetailsModel.setWalletID(UUID.randomUUID().toString());
		return userDetailsRepository.save(userDetailsModel);
	}

//	@Override
//	public boolean validateWalletID(UUID walletID) {
//		return userDetailsRepository.equals(walletID);
//	}

	@Override
	public boolean validateWalletID(Integer id) {
		return userDetailsRepository.existsById(id);
	}
}
