package com.project.bakery.model;

import lombok.Data;

@Data
public class UploadSlipRequest {

    private int orderId;
    private int userId;
    private String paymentSlip;
}
