package com.demo.discountservice.models;

import lombok.Data;

import java.util.List;

@Data
public class Bill {

    private User user;
    private List<Item> items;
}
