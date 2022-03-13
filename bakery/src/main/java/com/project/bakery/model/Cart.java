package com.project.bakery.model;

import lombok.Data;

@Data
public class Cart {

    private int productId;
    private int userId;
    private int id;
    private String description;
    private String title;
    private String img;
    private int price;
    private String tag;
    private int qty;

}
