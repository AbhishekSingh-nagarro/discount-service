package com.demo.discountservice.istrategy;

import com.demo.discountservice.models.Bill;

public interface DiscountStrategy {
    double applyDiscount(Bill bill);
}
