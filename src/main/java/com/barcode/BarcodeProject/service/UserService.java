package com.barcode.BarcodeProject.service;


import java.util.List;

import com.barcode.BarcodeProject.model.User;


public interface UserService {

	User save(User user);

	List<User> findAll();

	User getUserByEmail(String email);
    
	//User updateUserScore(User user, int id);
	
	
}
