package com.demo.discountservice.controller;

import com.demo.discountservice.models.Bill;
import com.demo.discountservice.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculateNetPayableAmount(@RequestBody Bill bill) {
            double amount = discountService.calculateNetPayableAmount(bill);
            return ResponseEntity.ok(amount);
    }
}