package com.barcode.BarcodeProject.web.impl;

import com.barcode.BarcodeProject.common.ApiMessage;
import com.barcode.BarcodeProject.dto.ProductDto.ForeignBarCodeDto;
import com.barcode.BarcodeProject.dto.ProductDto.ProductDto;
import com.barcode.BarcodeProject.model.Category;
import com.barcode.BarcodeProject.service.ProductService;
import com.barcode.BarcodeProject.web.IProductApi;
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
@Api(value = "productApi")
public class ProductApi implements IProductApi {

    @Autowired
    private ProductService productService;

    private Object httpResponseBody;
    private HttpStatus httpStatus;

    @Override
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto product) {
        try {
            httpResponseBody = productService.save(product);
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

            httpResponseBody = productService.findAll();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);

    }

    @Override
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto product, @PathVariable int productId) {
        try {

            Optional<ProductDto> updatedProduct = productService.update(product, productId);
            if (updatedProduct.isPresent()) {
                httpResponseBody = updatedProduct.get();
                httpStatus = HttpStatus.OK;

            } else {
                httpResponseBody = ApiMessage.PRODUCT_NOT_FOUND;
                httpStatus = HttpStatus.NOT_FOUND;
            }

        } catch (
                Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
        try {
            boolean deleteResponse = productService.delete(productId);
            if (deleteResponse) {
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
            Optional<ProductDto> existingProduct = productService.findById(productId);
            if (existingProduct.isPresent()) {
                httpResponseBody = existingProduct;
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

            httpResponseBody = productService.findByCategory(category);
            httpStatus = HttpStatus.OK;

        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> getLocalProductsByForeignBarcode(@RequestBody ForeignBarCodeDto foreignBarcodeDto) {
        try {
            httpResponseBody = productService.getLocalProductsByForeignBarcode(foreignBarcodeDto);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

    @Override
    public ResponseEntity<?> getLocalProductsByStringForeignBarcode(@RequestBody String foreignBarcode) {
        try {
            httpResponseBody = productService.getLocalProductsByStringForeignBarcode(foreignBarcode);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }

}
