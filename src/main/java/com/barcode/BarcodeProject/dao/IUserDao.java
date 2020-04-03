package com.barcode.BarcodeProject.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barcode.BarcodeProject.model.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>{

	User findByEmailIgnoreCase(String username);
     
	
	
}
