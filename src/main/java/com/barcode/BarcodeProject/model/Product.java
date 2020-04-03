package com.barcode.BarcodeProject.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="product")
public class Product implements Serializable {
    @Id
    @GeneratedValue (strategy= GenerationType.AUTO)

    private int id;
    @Column(name="name")
    private String name;
    @Column(name="factory")
    private String factory;
    @Column(name="image")
    private String image;
    @Column(name="points")
    private int points;
    
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

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
        final StringBuilder sb = new StringBuilder("Product{");
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
