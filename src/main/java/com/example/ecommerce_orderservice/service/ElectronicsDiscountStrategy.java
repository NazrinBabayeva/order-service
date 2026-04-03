package com.example.ecommerce_orderservice.service;

import org.springframework.stereotype.Component;

@Component
public class ElectronicsDiscountStrategy implements DiscountStrategy{
    @Override
    public double applyDiscount(double price) {
        return price * 0.95;
    }



}
