package com.barcode.BarcodeProject.web.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barcode.BarcodeProject.common.ApiMessage;
import com.barcode.BarcodeProject.dao.IUserDao;
import com.barcode.BarcodeProject.model.Product;
import com.barcode.BarcodeProject.model.User;
import com.barcode.BarcodeProject.web.IUserApi;
import com.barcode.BarcodeProject.web.ProductDto.ProductViewDto;
import com.barcode.BarcodeProject.web.UserDto.UserViewDto;
import com.barcode.BarcodeProject.web.UserDto.UserCreateOrUpdateDto;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin("*")
@Api(value = "userApi")
public class UserApi implements IUserApi{

	 @Autowired
	    private IUserDao userDao;
	    private Object httpResponseBody;
	    private HttpStatus httpStatus;
	    private ModelMapper modelMapper = new ModelMapper();
	@Override
	public ResponseEntity<?> saveUser(@RequestBody UserCreateOrUpdateDto user) {
		try {
            configureModeMapper();
            User userToBeSaved = modelMapper.map(user, User.class);
            User savedUser = userDao.save(userToBeSaved);
            UserViewDto userToBeReturned = modelMapper.map(savedUser, UserViewDto.class);
            httpResponseBody = userToBeReturned;
            httpStatus = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
	}

	@Override
	public ResponseEntity<?> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> updateUser(UserCreateOrUpdateDto user, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteUser(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> findByEmailAndPassword(@RequestBody String email, String password) {
		try {
            configureModeMapper();
            Optional<User> existingUser = userDao.findByEmailAndPassword(email, password);
            if (existingUser.isPresent()) {
                httpResponseBody = modelMapper.map(existingUser.get(), UserViewDto.class);
                httpStatus = HttpStatus.OK;
            } else {
                httpResponseBody = ApiMessage.USER_NOT_FOUND;
                httpStatus = HttpStatus.NOT_FOUND;
            }

        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
	}
	  private void configureModeMapper() {
	        modelMapper.getConfiguration().setAmbiguityIgnored(true);
	        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	    }
}
