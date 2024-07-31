package com.demo.discountservice.strategy;

import com.demo.discountservice.models.Bill;

public interface DiscountStrategy {
    double applyDiscount(Bill bill);
}
