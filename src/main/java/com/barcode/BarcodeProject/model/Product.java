package com.barcode.BarcodeProject.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "product")
@Data
@ToString
@NoArgsConstructor
@Builder
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "factory")
    private String factory;
    @Column(name = "image")
    private String image;
    @Column(name = "points")
    private int points;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;


}
