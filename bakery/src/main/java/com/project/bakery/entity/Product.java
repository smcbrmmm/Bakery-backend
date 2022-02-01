package com.project.bakery.entity;

import lombok.Data;

@Data
public class Product {

    private String name;
    private String img;
    private String price;
    private String type;

    public Product(String name, String img, String price , String type) {
        this.name = name;
        this.img = img;
        this.price = price;
        this.type = type;
    }

}
