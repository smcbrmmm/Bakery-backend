package com.project.bakery.model;

import lombok.Data;

@Data
public class User {
    private String email;
    private String password;
    private String tel;
    private String name;
    private String role;
    private String profilePic;
    private String tokenId;
}
