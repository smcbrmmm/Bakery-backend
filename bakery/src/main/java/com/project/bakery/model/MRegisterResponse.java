package com.project.bakery.model;

import lombok.Data;

@Data
public class MRegisterResponse {
    private String status;
    private String message;
    private String accessToken;
    private User user;
}
