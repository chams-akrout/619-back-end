package com.barcode.BarcodeProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barcode.BarcodeProject.model.Category;

public interface ICategoryDao extends JpaRepository<Category, Integer>{

//	Category findByName(String name);
}
