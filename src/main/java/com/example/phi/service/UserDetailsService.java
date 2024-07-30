package com.example.phi.service;

import com.example.phi.model.UserDetailsModel;

public interface UserDetailsService {

	public String registerUser(UserDetailsModel detailsModel);
	
//	public boolean validateWalletID(UUID walletID);
	
	public boolean validateWalletID(Integer id);

}
