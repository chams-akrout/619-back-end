package com.barcode.BarcodeProject.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.barcode.BarcodeProject.common.ApiMessage;
import com.barcode.BarcodeProject.common.ApiStatus;
import com.barcode.BarcodeProject.web.CategoryDto.CategoryCreateOrUpdateDto;
import com.barcode.BarcodeProject.web.CategoryDto.CategoryViewDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ICategoryApi {

	@PostMapping(path = "/categories")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Save a new category", response = CategoryViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> saveCategory(CategoryCreateOrUpdateDto category);

    @GetMapping(path = "/categories")
    @ApiOperation(value = "Get all categories", response = CategoryViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getAllCategories();

    @PutMapping(path = "/categories/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update an existing category by id", response = CategoryViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.ACCEPTED, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> updateCategory(CategoryCreateOrUpdateDto category, int categoryId);

    @DeleteMapping(path = "/categories/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete an existing category by id", response = CategoryViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> deleteCategory(int categoryId);

    @GetMapping(path = "/categories/{categoryId}")
    @ApiOperation(value = "Get an existing category by id", response = CategoryViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getCategoryById(int categoryId);
    
    

	
	
}
