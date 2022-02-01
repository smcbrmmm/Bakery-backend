package com.project.bakery.entity;

import lombok.Data;

@Data
public class User {

    private String email;
    private String password;

    public User(String email , String password){
        this.email = email ;
        this.password = password;
    }

}
