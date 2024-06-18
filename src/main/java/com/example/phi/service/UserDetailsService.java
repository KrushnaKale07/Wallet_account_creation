package com.example.phi.service;

import com.example.phi.model.UserDetailsModel;

public interface UserDetailsService {
	
	public UserDetailsModel registerUser(UserDetailsModel detailsModel);

	public UserDetailsModel fetchUserDetails(UserDetailsModel userDetailsModel);

	UserDetailsModel findByWalletId(String walletId);
	
}
