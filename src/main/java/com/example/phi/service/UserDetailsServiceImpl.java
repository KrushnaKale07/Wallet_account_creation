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

	UserDetailsModel result = null;

//	@Override
//	public UserDetailsModel creareNewUser(UserDetailsModel detailsModel) {
//
//		try {
//			result = userDetailsRepository.save(detailsModel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

	@Override
	public UserDetailsModel fetchUserDetails(UserDetailsModel userDetailsModel) {
		try {
			result = userDetailsRepository.getById(userDetailsModel.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public UserDetailsModel findByWalletId(String walletId) {
		return result;
	}

	public UserDetailsModel registerUser(UserDetailsModel userDetailsModel) {
		userDetailsModel.setWalletID(UUID.randomUUID().toString());
		return userDetailsRepository.save(userDetailsModel);
	}
}
