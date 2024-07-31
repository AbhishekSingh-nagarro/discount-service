package com.demo.discountservice.strategy;

import com.demo.discountservice.models.Bill;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(Bill bill) {
        return 0;
    }
}