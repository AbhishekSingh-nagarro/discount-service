package com.demo.discountservice.service;

import com.demo.discountservice.exception.InvalidBillException;
import com.demo.discountservice.exception.InvalidUserException;
import com.demo.discountservice.models.Bill;
import com.demo.discountservice.models.Item;
import com.demo.discountservice.models.User;
import com.demo.discountservice.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountServiceTest {

    @InjectMocks
    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private User createUser(boolean isEmployee, boolean isAffiliate, boolean isLongTermCustomer) {
        User user = new User();
        user.setIsEmployee(isEmployee);
        user.setIsAffiliate(isAffiliate);
        user.setIsLongTermCustomer(isLongTermCustomer);
        return user;
    }

    private Item createItem(double price, boolean isGrocery) {
        Item item = new Item();
        item.setPrice(price);
        item.setIsGrocery(isGrocery);
        return item;
    }

    private Bill createBill(User user, Item... items) {
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setItems(Arrays.asList(items));
        return bill;
    }

    @Test
    public void testEmployeeDiscount() {
        User user = createUser(true, false, false);
        Item item1 = createItem(200, false);
        Item item2 = createItem(300, false);
        Bill bill = createBill(user, item1, item2);

        double result = discountService.calculateNetPayableAmount(bill);
        assertEquals(325, result, 0.0);
    }

    @Test
    public void testAffiliateDiscount() {
        User user = createUser(false, true, false);
        Item item1 = createItem(200, false);
        Item item2 = createItem(300, false);
        Bill bill = createBill(user, item1, item2);

        double result = discountService.calculateNetPayableAmount(bill);
        assertEquals(425, result, 0.0);
    }

    @Test
    public void testLongTermCustomerDiscount() {
        User user = createUser(false, false, true);
        Item item1 = createItem(200, false);
        Item item2 = createItem(300, false);
        Bill bill = createBill(user, item1, item2);

        double result = discountService.calculateNetPayableAmount(bill);
        assertEquals(450, result, 0.0);
    }

    @Test
    public void testNoDiscount() {
        User user = createUser(false, false, false);
        Item item1 = createItem(200, false);
        Item item2 = createItem(300, false);
        Bill bill = createBill(user, item1, item2);

        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> {
            discountService.calculateNetPayableAmount(bill);
        });

        assertEquals(Constant.USER_NOT_ELIGIBLE, exception.getMessage());
    }

    @Test
    public void testGroceryItems() {
        User user = createUser(true, false, false);
        Item item1 = createItem(200, true);
        Item item2 = createItem(300, false);
        Bill bill = createBill(user, item1, item2);

        double result = discountService.calculateNetPayableAmount(bill);
        assertEquals(385, result, 0.0);
    }

    @Test
    public void testMixedItems() {
        User user = createUser(true, false, false);
        Item item1 = createItem(100, true);
        Item item2 = createItem(100, false);
        Bill bill = createBill(user, item1, item2);

        double result = discountService.calculateNetPayableAmount(bill);
        assertEquals(160, result, 0.0);
    }

    @Test
    public void testInvalidBill() {
        Bill bill = new Bill(); // No user and items
        InvalidBillException exception = assertThrows(InvalidBillException.class, () -> {
            discountService.calculateNetPayableAmount(bill);
        });
        assertEquals(Constant.BILL_INFO_MISSING, exception.getMessage());
    }

    @Test
    public void testInvalidUser() {
        User user = createUser(false, false, false);
        Item item1 = createItem(200, false);
        Item item2 = createItem(300, false);
        Bill bill = createBill(user, item1, item2);

        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> {
            discountService.calculateNetPayableAmount(bill);
        });
        assertEquals(Constant.USER_NOT_ELIGIBLE, exception.getMessage());
    }
}
