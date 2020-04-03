package com.barcode.BarcodeProject.web.impl;

import com.barcode.BarcodeProject.common.ApiMessage;
import com.barcode.BarcodeProject.dao.IProductDao;
import com.barcode.BarcodeProject.model.Category;
import com.barcode.BarcodeProject.model.Product;
import com.barcode.BarcodeProject.web.IProductApi;
import com.barcode.BarcodeProject.web.ProductDto.ProductCreateOrUpdateDto;
import com.barcode.BarcodeProject.web.ProductDto.ProductViewDto;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@Api(value = "productApi")
public class ProductApi implements IProductApi {
    @Autowired
    private IProductDao productDao;
    private Object httpResponseBody;
    private HttpStatus httpStatus;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseEntity<?> saveProduct(@RequestBody ProductCreateOrUpdateDto product) {
        try {
            configureModeMapper();
            Product productToBeSaved = modelMapper.map(product, Product.class);
            Product savedProduct = productDao.save(productToBeSaved);
            ProductViewDto productToBeReturned = modelMapper.map(savedProduct, ProductViewDto.class);
            httpResponseBody = productToBeReturned;
            httpStatus = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);

    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        try {
            configureModeMapper();
            List<Product> products = productDao.findAll();
            httpResponseBody = !products.isEmpty() ? products.stream().map(product -> modelMapper.map(product, ProductViewDto.class)).collect(Collectors.toList()) : Collections.emptyList();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);

    }

    @Override
    public ResponseEntity<?> updateProduct(@RequestBody ProductCreateOrUpdateDto product, @PathVariable int productId) {
        try {
            configureModeMapper();
            Optional<Product> existingProduct = productDao.findById(productId);
            if (existingProduct.isPresent()) {
                Product productToBeUpdated = modelMapper.map(product, Product.class);
                productToBeUpdated.setId(productId);
                Product updatedProduct = productDao.save(productToBeUpdated);
                httpResponseBody = modelMapper.map(updatedProduct, ProductViewDto.class);
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

    @Override
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
        try {

            Optional<Product> existingProduct = productDao.findById(productId);
            if (existingProduct.isPresent()) {
                productDao.delete(existingProduct.get());
                httpResponseBody = ApiMessage.PRODUCT_DELETED_SUCCESSFULLY;
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

    @Override
    public ResponseEntity<?> getProductById(@PathVariable int productId) {
        try {
            configureModeMapper();
            Optional<Product> existingProduct = productDao.findById(productId);
            if (existingProduct.isPresent()) {
                httpResponseBody = modelMapper.map(existingProduct.get(), ProductViewDto.class);
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

    @Override
    public ResponseEntity<?> getProductByCategory(@RequestBody Category category) {
        try {
            configureModeMapper();
            List<Product> products = productDao.findByCategory(category);

            httpResponseBody = !products.isEmpty() ? products.stream().map(product -> modelMapper.map(product, ProductViewDto.class)).collect(Collectors.toList()) : Collections.emptyList();
            httpStatus = HttpStatus.OK;

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
