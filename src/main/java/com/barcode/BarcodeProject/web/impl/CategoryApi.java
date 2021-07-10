package com.barcode.BarcodeProject.web.impl;

import com.barcode.BarcodeProject.common.ApiMessage;
import com.barcode.BarcodeProject.dto.CategoryDto.CategoryDto;
import com.barcode.BarcodeProject.service.CategoryService;
import com.barcode.BarcodeProject.web.ICategoryApi;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@Api(value = "categoryApi")
public class CategoryApi implements ICategoryApi {

    @Autowired
    private CategoryService categoryService;
    private Object httpResponseBody;
    private HttpStatus httpStatus;


    @Override
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto category) {
        try {
            httpResponseBody = categoryService.saveCategory(category);
            httpStatus = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        try {

            httpResponseBody = categoryService.findAll();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);

    }

    @Override
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto category, @PathVariable int categoryId) {
        try {

            Optional<CategoryDto> updatedCategory = categoryService.update(category, categoryId);
            if (updatedCategory.isPresent()) {
                httpResponseBody = updatedCategory;
                httpStatus = HttpStatus.OK;
            } else {
                httpResponseBody = ApiMessage.CATEGORY_NOT_FOUND;
                httpStatus = HttpStatus.NOT_FOUND;
            }

        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryId) {
        try {

            boolean deleteResponse = categoryService.delete(categoryId);
            if (deleteResponse) {
                httpResponseBody = ApiMessage.CATEGORY_DELETED_SUCCESSFULLY;
                httpStatus = HttpStatus.OK;
            } else {
                httpResponseBody = ApiMessage.CATEGORY_NOT_FOUND;
                httpStatus = HttpStatus.NOT_FOUND;
            }

        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> getCategoryById(@PathVariable int categoryId) {
        try {

            Optional<CategoryDto> existingCategory = categoryService.findById(categoryId);
            if (existingCategory.isPresent()) {
                httpResponseBody = existingCategory;
                httpStatus = HttpStatus.OK;
            } else {
                httpResponseBody = ApiMessage.CATEGORY_NOT_FOUND;
                httpStatus = HttpStatus.NOT_FOUND;
            }

        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

}
