package com.barcode.BarcodeProject.web.CategoryDto;

import java.util.List;

import com.barcode.BarcodeProject.model.Product;

public class CategoryCreateOrUpdateDto {

	    private String name;
	    private List<Product> products;
	    
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<Product> getProducts() {
			return products;
		}
		public void setProducts(List<Product> products) {
			this.products = products;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("CategoryCreateOrUpdateDto [name=");
			builder.append(name);
			builder.append(", products=");
			builder.append(products);
			builder.append("]");
			return builder.toString();
		}
	    
	    
}
