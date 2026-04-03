package com.example.ecommerce_orderservice.service;

import com.example.ecommerce_orderservice.enums.Category;

public class DiscountStrategyFactory {

    public static DiscountStrategy getStrategy(Category category){

        switch (category){

            case ELECTRONICS:
                return new ElectronicsDiscountStrategy();

            case CLOTHES:
                return new ClothesDiscountStrategy();

            case BOOKS:
                return new BooksDiscountStrategy();

            default:
                throw new RuntimeException("Category not supported");
        }
    }
}
