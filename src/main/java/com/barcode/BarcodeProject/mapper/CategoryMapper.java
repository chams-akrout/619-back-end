package com.barcode.BarcodeProject.mapper;

import com.barcode.BarcodeProject.dto.CategoryDto.CategoryDto;
import com.barcode.BarcodeProject.model.Category;

public final class CategoryMapper {

    public static Category toCategory(CategoryDto categoryDto){
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }

    public static CategoryDto toCategoryDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
