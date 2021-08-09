package com.barcode.BarcodeProject.mapper;

import com.barcode.BarcodeProject.dto.productDto.ProductDto;
import com.barcode.BarcodeProject.model.Product;

public final class ProductMapper {

    public static Product toProduct(ProductDto productDto){
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .factory(productDto.getFactory())
                .image(productDto.getImage())
                .points(productDto.getPoints())
                .build();
    }

    public static ProductDto toProductDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .factory(product.getFactory())
                .image(product.getImage())
                .points(product.getPoints())
                .category(CategoryMapper.toCategoryDto(product.getCategory()))
                .categoryId(product.getCategory().getId())
                .build();
    }

}
