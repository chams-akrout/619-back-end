package com.barcode.BarcodeProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.barcode.BarcodeProject.model.Category;
@Repository
public interface ICategoryDao extends JpaRepository<Category, Integer>{

}
