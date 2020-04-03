package com.barcode.BarcodeProject.web.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barcode.BarcodeProject.common.ApiMessage;
import com.barcode.BarcodeProject.dao.ICategoryDao;
import com.barcode.BarcodeProject.model.Category;
import com.barcode.BarcodeProject.web.ICategoryApi;
import com.barcode.BarcodeProject.web.CategoryDto.CategoryCreateOrUpdateDto;
import com.barcode.BarcodeProject.web.CategoryDto.CategoryViewDto;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin("*")
@Api(value = "categoryApi")
public class CategoryApi implements ICategoryApi{

	@Autowired
    private ICategoryDao categoryDao;
    private Object httpResponseBody;
    private HttpStatus httpStatus;
    private ModelMapper modelMapper = new ModelMapper();

	
	@Override
	public ResponseEntity<?> saveCategory(@RequestBody CategoryCreateOrUpdateDto category) {
		 try {
	            configureModeMapper();
	            Category categoryToBeSaved = modelMapper.map(category, Category.class);
	            Category savedCategory = categoryDao.save(categoryToBeSaved);
	            CategoryViewDto categoryToBeReturned = modelMapper.map(savedCategory, CategoryViewDto.class);
	            httpResponseBody = categoryToBeReturned;
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
	            configureModeMapper();
	            List<Category> categories = categoryDao.findAll();
	            httpResponseBody = !categories.isEmpty() ? categories.stream().map(category -> modelMapper.map(category, CategoryViewDto.class)).collect(Collectors.toList()) : Collections.emptyList();
	            httpStatus = HttpStatus.OK;
	        } catch (Exception e) {
	            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
	            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(httpResponseBody, httpStatus);

	}

	@Override
	public ResponseEntity<?> updateCategory(@RequestBody CategoryCreateOrUpdateDto category, @PathVariable int categoryId) {
        try {
            configureModeMapper();
            Optional<Category> existingCategory = categoryDao.findById(categoryId);
            if (existingCategory.isPresent()) {
            	Category categoryToBeUpdated = modelMapper.map(category, Category.class);
            	categoryToBeUpdated.setId(categoryId);
            	Category updatedCategory = categoryDao.save(categoryToBeUpdated);
                httpResponseBody = modelMapper.map(updatedCategory, CategoryViewDto.class);
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

	            Optional<Category> existingCategory = categoryDao.findById(categoryId);
	            if (existingCategory.isPresent()) {
	            	categoryDao.delete(existingCategory.get());
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
	            configureModeMapper();
	            Optional<Category> existingCategory = categoryDao.findById(categoryId);
	            if (existingCategory.isPresent()) {
	                httpResponseBody = modelMapper.map(existingCategory.get(), CategoryViewDto.class);
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

	
	private void configureModeMapper() {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
}
