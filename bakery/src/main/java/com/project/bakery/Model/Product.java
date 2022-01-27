package com.project.bakery.Model;

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

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }
}
