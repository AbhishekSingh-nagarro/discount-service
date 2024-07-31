package com.demo.discountservice.service;

import com.demo.discountservice.exception.InvalidBillException;
import com.demo.discountservice.exception.InvalidUserException;
import com.demo.discountservice.factory.DiscountFactory;
import com.demo.discountservice.models.Bill;
import com.demo.discountservice.models.Item;
import com.demo.discountservice.models.User;
import com.demo.discountservice.istrategy.DiscountStrategy;
import com.demo.discountservice.utils.Constant;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public double calculateNetPayableAmount(Bill bill) {
        validateBill(bill);

        DiscountStrategy strategy = DiscountFactory.getDiscountStrategy(bill.getUser());
        double percentageDiscount = strategy.applyDiscount(bill);

        double total = bill.getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum();

        double discountPerHundred = (int) (total / 100) * 5;

        return total - percentageDiscount - discountPerHundred;
    }

    private void validateBill(Bill bill) {
        if (bill == null || bill.getUser() == null) {
            throw new InvalidBillException(Constant.BILL_INFO_MISSING);
        }

        User user = bill.getUser();
        if (!user.getIsEmployee() && !user.getIsAffiliate() && !user.getIsLongTermCustomer()) {
            throw new InvalidUserException(Constant.USER_NOT_ELIGIBLE);
        }
    }
}