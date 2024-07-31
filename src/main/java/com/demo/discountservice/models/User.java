package com.demo.discountservice.models;

import lombok.Data;

@Data
public class User {

    private Boolean isEmployee;
    private Boolean isAffiliate;
    private Boolean isLongTermCustomer;
}
