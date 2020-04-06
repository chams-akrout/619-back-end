package com.barcode.BarcodeProject.web.ProductDto;

import com.barcode.BarcodeProject.model.Category;
import com.barcode.BarcodeProject.web.CategoryDto.CategoryViewDto;

public class ProductViewDto {
    private int id;
    private String name;
    private CategoryViewDto category;
    private String factory;
    private String image;
    private int points;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryViewDto getCategory() {
        return category;
    }

    public void setCategory(CategoryViewDto category) {
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
        final StringBuilder sb = new StringBuilder("ProductViewDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", factory='").append(factory).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", points='").append(points).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
