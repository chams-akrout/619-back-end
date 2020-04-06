package com.barcode.BarcodeProject.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "category")
public class Category implements Serializable {
	    @Id
	    @GeneratedValue (strategy= GenerationType.AUTO)
	    private int id;
	    
	    @Column(name="name")
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
			builder.append("Category [id=");
			builder.append(id);
			builder.append(", name=");
			builder.append(name);
			builder.append("]");
			return builder.toString();
		}
	    
	    
}
