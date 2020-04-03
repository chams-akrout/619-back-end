package com.barcode.BarcodeProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.barcode.BarcodeProject.dao.IUserDao;
import com.barcode.BarcodeProject.model.User;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired 
	private IUserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user=userDao.findByEmailIgnoreCase(username);
	if(user==null) {
		throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		
	}else {
		
		return JwtUserFactory.create(user);
	}
		
	}
	
	
}
