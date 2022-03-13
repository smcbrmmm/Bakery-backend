package com.project.bakery.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDetailResponse {
    private int orderId;
    private int productId;
    private int productPrice;
    private int productQty;
    private String title;
    private String img;
    private String tag;
    private String status;
    private String date;


}
