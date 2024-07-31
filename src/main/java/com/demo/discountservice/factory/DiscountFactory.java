package com.demo.discountservice.factory;

import com.demo.discountservice.models.User;
import com.demo.discountservice.strategy.*;

public class DiscountFactory {
    public static DiscountStrategy getDiscountStrategy(User user) {
        if (user.getIsEmployee()) {
            return new EmployeeDiscount();
        } else if (user.getIsAffiliate()) {
            return new AffiliateDiscount();
        } else if (user.getIsLongTermCustomer()) {
            return new LongTermCustomerDiscount();
        } else {
            return new NoDiscount();
        }
    }
}