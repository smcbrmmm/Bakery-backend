package com.project.bakery.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private String type;
    private String productDetail;
    private String img;
}
