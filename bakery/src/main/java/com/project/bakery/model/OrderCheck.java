package com.project.bakery.model;

import lombok.Data;

@Data
public class OrderCheck {
    private int orderId;
    private int userId;
    private String email;
    private String status;
    private String date;
    private int sumPrice;
    private String hasPayment;
    private String recieverName ;
    private String recieverTel ;
    private String postal ;
    private String province ;
    private String place ;
    private String houseNumber ;
    private String address ;
}
