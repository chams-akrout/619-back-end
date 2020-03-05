package com.barcode.BarcodeProject.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barcode.BarcodeProject.model.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>{
//	User findByEmail(String email);
//	User findByName(String name);
	Optional<User> findByEmailAndPassword(String email,String password);
}
