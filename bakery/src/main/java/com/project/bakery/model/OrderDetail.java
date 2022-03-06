package com.project.bakery.model;

import lombok.Data;

@Data
public class OrderDetail {

    private int orderDetailId;
    private int orderId;
    private int productId;
    private int productPrice;
    private int productQty;
    private int userId;

}
