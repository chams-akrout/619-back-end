package com.barcode.BarcodeProject.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barcode.BarcodeProject.common.ApiMessage;
import com.barcode.BarcodeProject.dao.IUserDao;
import com.barcode.BarcodeProject.model.User;
import com.barcode.BarcodeProject.service.UserService;
import com.barcode.BarcodeProject.web.UserDto.UserCreateOrUpdateDto;
import com.barcode.BarcodeProject.web.UserDto.UserViewDto;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private IUserDao userDao;
	private Object httpResponseBody;
	private HttpStatus httpStatus;
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping(value="/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users= userService.findAll();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		
	}
	
	@PutMapping(value="/users/updateUserScore/{userId}")
	public ResponseEntity<?> updateUserScore(@RequestBody UserCreateOrUpdateDto user,@PathVariable  int userId) {
		try {
			configureModeMapper();
			Optional<User> existingUser = userDao.findById(userId);
			if (existingUser.isPresent()) {
				User userToBeUpdated = modelMapper.map(user, User.class);
				userToBeUpdated.setId(userId);
				userToBeUpdated.setScore(userToBeUpdated.getScore()+5);;
				User updatedUser = userDao.save(userToBeUpdated);
				httpResponseBody = modelMapper.map(updatedUser, UserViewDto.class);
				httpStatus = HttpStatus.OK;
			} else {
				httpResponseBody = ApiMessage.PRODUCT_NOT_FOUND;
				httpStatus = HttpStatus.NOT_FOUND;
			}

		} catch (Exception e) {
			httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpResponseBody, httpStatus);
	}
	
	@GetMapping(value="/getuser")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User> getUser(Principal principal){
		User user= userService.getUserByEmail(principal.getName());
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	private void configureModeMapper() {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
}
