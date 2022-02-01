package com.project.bakery.entity;

import lombok.Data;

@Data
public class Customer {
    private int id;
    private String name;
    private String pin;

    public Customer(int id, String name, String pin) {
        this.id = id;
        this.name = name;
        this.pin = pin;
    }

}
