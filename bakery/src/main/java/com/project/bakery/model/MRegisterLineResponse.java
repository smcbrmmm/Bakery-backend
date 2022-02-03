package com.project.bakery.model;

import lombok.Data;

@Data
public class MRegisterLineResponse {
    private String email;
    private String tokenId;
    private String name;
}
