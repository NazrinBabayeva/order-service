package com.example.ecommerce_orderservice.service;

public class OthersDiscountStrategy implements DiscountStrategy{

    @Override
    public double applyDiscount(double price) {
        return price * 0.95;
    }
}
