package com.demo.discountservice.strategyimpl;

import com.demo.discountservice.istrategy.DiscountStrategy;
import com.demo.discountservice.models.Bill;
import com.demo.discountservice.models.Item;

public class EmployeeDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(Bill bill) {
        double total = bill.getItems().stream()
                .filter(item -> !item.getIsGrocery())
                .mapToDouble(Item::getPrice)
                .sum();
        return total * 0.30;
    }
}