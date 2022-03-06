package com.project.bakery.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private int id;
    private String description;
    private String title;
    private String name;
    private String img;
    private int price;
    private String tag;
    private int qty;
}
