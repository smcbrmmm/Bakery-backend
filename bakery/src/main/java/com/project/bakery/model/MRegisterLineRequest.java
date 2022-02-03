package com.project.bakery.model;

import lombok.Data;

@Data
public class MRegisterLineRequest {
    private String email;
    private String tokenId;
    private String name;
}
