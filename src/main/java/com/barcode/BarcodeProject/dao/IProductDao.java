package com.barcode.BarcodeProject.dao;

import com.barcode.BarcodeProject.model.Category;
import com.barcode.BarcodeProject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(Category category);
}
