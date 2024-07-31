package com.demo.discountservice.strategy;

import com.demo.discountservice.models.Bill;
import com.demo.discountservice.models.Item;

public class LongTermCustomerDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(Bill bill) {
        double total = bill.getItems().stream()
                .filter(item -> !item.getIsGrocery())
                .mapToDouble(Item::getPrice)
                .sum();
        return total * 0.05;
    }
}