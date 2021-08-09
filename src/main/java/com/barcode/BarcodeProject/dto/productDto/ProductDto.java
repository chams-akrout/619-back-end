package com.barcode.BarcodeProject.dto.productDto;

import com.barcode.BarcodeProject.dto.categoryDto.CategoryDto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Builder
public class ProductDto {

    private int id;
    private String name;
    private String factory;
    private String image;
    private int points;
    private CategoryDto category;
    private int categoryId;

}
