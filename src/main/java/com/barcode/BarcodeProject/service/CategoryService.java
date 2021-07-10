package com.barcode.BarcodeProject.service;

import com.barcode.BarcodeProject.dao.ICategoryDao;

import com.barcode.BarcodeProject.dto.categoryDto.CategoryDto;
import com.barcode.BarcodeProject.mapper.CategoryMapper;
import com.barcode.BarcodeProject.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    public CategoryDto saveCategory(CategoryDto category) {
        Category savedCategory = categoryDao.save(CategoryMapper.toCategory(category));
        return CategoryMapper.toCategoryDto(savedCategory);
    }

    public List<CategoryDto> findAll(){
        List <Category> categories = categoryDao.findAll();
        return !categories.isEmpty() ? categories.stream()
                .map(CategoryMapper::toCategoryDto).collect(Collectors.toList()) : Collections.emptyList();
    }

    public Optional<CategoryDto> update(CategoryDto categoryDto, int id){
        Optional <Category> existingCategory= categoryDao.findById(id);
        if(existingCategory.isPresent()){
            Category updatedCategory = categoryDao.save(CategoryMapper.toCategory(categoryDto));
            return Optional.of(CategoryMapper.toCategoryDto(updatedCategory));
        }
        return Optional.empty();
    }

    public boolean delete(int id) {
        Optional<Category> existingCategory = categoryDao.findById(id);
        if (existingCategory.isPresent()) {
            categoryDao.delete(existingCategory.get());
            return true;
        }
        return false;
    }

    public Optional<CategoryDto> findById(int id) {
        Optional<Category> existingCategory = categoryDao.findById(id);
        return existingCategory.map(CategoryMapper::toCategoryDto);
    }

}
