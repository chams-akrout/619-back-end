package com.barcode.BarcodeProject.web;

import com.barcode.BarcodeProject.common.ApiMessage;
import com.barcode.BarcodeProject.common.ApiStatus;
import com.barcode.BarcodeProject.model.Category;
import com.barcode.BarcodeProject.model.Product;
import com.barcode.BarcodeProject.web.ProductDto.ProductCreateOrUpdateDto;
import com.barcode.BarcodeProject.web.ProductDto.ProductViewDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface IProductApi {
    @PostMapping(path = "/products")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Save a new product", response = ProductViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> saveProduct(ProductCreateOrUpdateDto product);

    @GetMapping(path = "/products")
    @ApiOperation(value = "Get all products", response = ProductViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getAllProducts();

    @PutMapping(path = "/products/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update an existing product by id", response = ProductViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.ACCEPTED, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> updateProduct(ProductCreateOrUpdateDto product, int productId);

    @DeleteMapping(path = "/products/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete an existing product by id", response = ProductViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> deleteProduct(int productId);

    @GetMapping(path = "/products/{productId}")
    @ApiOperation(value = "Get an existing product by id", response = ProductViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getProductById(int productId);

    @PostMapping(path = "/products/category")
    @ApiOperation(value = "Get an existing product by category", response = ProductViewDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getProductByCategory(Category category);


}
