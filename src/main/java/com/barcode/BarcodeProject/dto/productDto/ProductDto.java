package com.barcode.BarcodeProject.dto.productDto;

import com.barcode.BarcodeProject.model.Category;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
@Builder
public class ProductDto {

    private int id;
    private String name;
    private String factory;
    private String image;
    private int points;
    private Category category;

}
