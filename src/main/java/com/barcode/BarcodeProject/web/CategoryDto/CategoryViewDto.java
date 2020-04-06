package com.barcode.BarcodeProject.web.CategoryDto;

import java.util.List;


import com.barcode.BarcodeProject.model.Product;
import com.barcode.BarcodeProject.web.ProductDto.ProductViewDto;

public class CategoryViewDto {

	private int id;
    private String name;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoryViewDto [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
    
    
	
}
