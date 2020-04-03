package com.barcode.BarcodeProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barcode.BarcodeProject.domain.Response;
import com.barcode.BarcodeProject.model.User;
import com.barcode.BarcodeProject.service.UserService;


@RestController
@CrossOrigin("*")
public class PreLoginController {

	@Autowired 
	private UserService userService;
	
	@PostMapping(value = "/registration")
	public ResponseEntity<Response> registration (@RequestBody User user){
		
	User dbUser=userService.save(user);
	if(dbUser!=null) {
		return new ResponseEntity<Response> ( new Response("User is saved successfully"),HttpStatus.OK);
	}
	return null;
    }
	
}
