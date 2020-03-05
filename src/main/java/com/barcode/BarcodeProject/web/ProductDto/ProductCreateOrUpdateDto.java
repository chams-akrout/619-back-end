package com.barcode.BarcodeProject.web.ProductDto;

import javax.persistence.Column;

public class ProductCreateOrUpdateDto {

    private String name;
    private String category;
    private String factory;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
        sb.append('}');
        return sb.toString();
    }
}
