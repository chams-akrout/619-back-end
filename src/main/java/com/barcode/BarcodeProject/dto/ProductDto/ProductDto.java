package com.barcode.BarcodeProject.dto.ProductDto;

import com.barcode.BarcodeProject.model.Category;

public class ProductCreateOrUpdateDto {

    private String name;
    private Category category;
    private String factory;
    private String image;
    private int points;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductCreateOrUpdateDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", factory='").append(factory).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", points='").append(points).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
