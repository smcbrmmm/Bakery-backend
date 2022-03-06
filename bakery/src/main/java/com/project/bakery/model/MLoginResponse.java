package com.project.bakery.model;

import lombok.Data;

@Data
public class MLoginResponse {
    private String status;
    private String message;
    private String accessToken;
    private User user;
}
