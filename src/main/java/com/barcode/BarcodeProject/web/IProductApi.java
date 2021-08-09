package com.barcode.BarcodeProject.web;

import com.barcode.BarcodeProject.common.ApiMessage;
import com.barcode.BarcodeProject.common.ApiStatus;
import com.barcode.BarcodeProject.dto.categoryDto.CategoryDto;
import com.barcode.BarcodeProject.model.Category;
import com.barcode.BarcodeProject.dto.productDto.ForeignBarCodeDto;
import com.barcode.BarcodeProject.dto.productDto.ProductDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface IProductApi {
    @PostMapping(path = "/products")
    @ApiOperation(value = "Save a new product", response = ProductDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> saveProduct(ProductDto product);

    @GetMapping(path = "/products")
    @ApiOperation(value = "Get all products", response = ProductDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getAllProducts();

    @PutMapping(path = "/products/{productId}")
    @ApiOperation(value = "Update an existing product by id", response = ProductDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.ACCEPTED, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> updateProduct(ProductDto product, int productId);

    @DeleteMapping(path = "/products/{productId}")
    @ApiOperation(value = "Delete an existing product by id", response = ProductDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> deleteProduct(int productId);

    @GetMapping(path = "/products/{productId}")
    @ApiOperation(value = "Get an existing product by id", response = ProductDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getProductById(int productId);

    @PostMapping(path = "/products/category")
    @ApiOperation(value = "Get an existing product by category", response = ProductDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getProductByCategory(Category category);
    
    @PostMapping(path = "/products/foreignBarcode")
    @ApiOperation(value = "Get a list of local products by foreign barcode", response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getLocalProductsByForeignBarcode(ForeignBarCodeDto foreignBarcode);
    
    @PostMapping(path = "/products/longForeignBarcode")
    @ApiOperation(value = "Get a list of local products by long foreign barcode", response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = ApiStatus.STATUS_OK, message = ApiMessage.SUCCESSFUL_OPERATION, responseContainer = "List")})
    public ResponseEntity<?> getLocalProductsByLongForeignBarcode(long foreignBarcode);
    
}
