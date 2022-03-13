package com.project.bakery.model;

import lombok.Data;

import java.util.Date;

@Data
public class Payment {
    private int paymentId;
    private int orderId;
    private int userId;
    private String paymentSlip;

}
