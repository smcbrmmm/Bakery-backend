package com.project.bakery.model;

import lombok.Data;

@Data
public class Address {
    private int userId;
    private int addressId;
    private String recieverName;
    private String recieverTel;
    private String postal;
    private String province;
    private String houseNumber;
    private String address;
    private String place;
}
