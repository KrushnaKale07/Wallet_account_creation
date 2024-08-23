package com.example.phi.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.phi.config.RedisConfig;
import com.example.phi.model.UserDetailsModel;
import com.example.phi.repository.UserDetailsRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Override
	public String registerUser(UserDetailsModel userDetailsModel) {
		logger.info("inside UserDetailsServiceImpl.registerUser()");
		userDetailsModel.setWalletID(UUID.randomUUID().toString());
		userDetailsRepository.save(userDetailsModel);
		return "Your user id is = '" + userDetailsModel.getId() + "' and your wallet id is = '"
				+ userDetailsModel.getWalletID() + "'. You can search your registory using your user id.";
	}

//	@Override
//	public boolean validateWalletID(UUID walletID) {
//		return userDetailsRepository.equals(walletID);
//	}

	@Override
	public boolean validateWalletID(Integer id) {
		logger.info("inside UserDetailsServiceImpl.validateWalletID()");
		return userDetailsRepository.existsById(id);
	}
}
