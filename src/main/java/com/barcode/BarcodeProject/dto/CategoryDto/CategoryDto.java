package com.barcode.BarcodeProject.dto.CategoryDto;

public class CategoryCreateOrUpdateDto {

	    private String name;
	    
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("CategoryCreateOrUpdateDto [name=");
			builder.append(name);
			builder.append("]");
			return builder.toString();
		}
	    
	    
}