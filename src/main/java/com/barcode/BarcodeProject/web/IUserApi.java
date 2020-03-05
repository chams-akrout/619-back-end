package com.barcode.BarcodeProject.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.barcode.BarcodeProject.common.ApiMessage;
import com.barcode.BarcodeProject.common.ApiStatus;
import com.barcode.BarcodeProject.web.UserDto.UserCreateOrUpdateDto;
import com.barcode.BarcodeProject.web.UserDto.UserViewDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IUserApi {
	@PostMapping(path = "/users")
    @ApiOperation(value = "Save a new user", response = UserViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> saveUser(UserCreateOrUpdateDto user);

    @GetMapping(path = "/users")
    @ApiOperation(value = "Get all users", response = UserViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getAllUsers();

    @PutMapping(path = "/users/{userId}")
    @ApiOperation(value = "Update an existing user by id", response = UserViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.ACCEPTED, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> updateUser(UserCreateOrUpdateDto user, int userId);

    @DeleteMapping(path = "/users/{userId}")
    @ApiOperation(value = "Delete an existing user by id", response = UserViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> deleteUser(int productId);

    @GetMapping(path = "/users/{userId}")
    @ApiOperation(value = "Get an existing users by id", response = UserViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getUserById(int userId);

    @PostMapping(path = "/home")
    @ApiOperation(value = "Get an existing user by email ", response = UserViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> findByEmail(String email);

}
