package com.project.bakery.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private int orderId;
    private int userId;
    private int addressId;
    private String status;
    private String date;
    private int sumPrice;
    private String hasPayment;
}
