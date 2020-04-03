package com.barcode.BarcodeProject.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barcode.BarcodeProject.dao.IUserDao;
import com.barcode.BarcodeProject.model.User;
import com.barcode.BarcodeProject.util.PasswordUtil;


@Service
@Transactional
public class UserServiceImpl implements UserService{

	
	 @Autowired
	 IUserDao userDao;
	 
	@Override
	public User save(User user) {
	String password=PasswordUtil.getPasswordHash(user.getPassword());
	user.setPassword(password);
	
		return userDao.save(user);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.findByEmailIgnoreCase(email);
	}
	
	

	
	
}
