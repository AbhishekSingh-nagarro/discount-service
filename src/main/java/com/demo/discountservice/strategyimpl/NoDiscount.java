package com.demo.discountservice.strategyimpl;

import com.demo.discountservice.istrategy.DiscountStrategy;
import com.demo.discountservice.models.Bill;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(Bill bill) {
        return 0;
    }
}