package com.demo.discountservice.models;

import lombok.Data;

@Data
public class Item {

    private String name;
    private double price;
    private Boolean isGrocery;
}
