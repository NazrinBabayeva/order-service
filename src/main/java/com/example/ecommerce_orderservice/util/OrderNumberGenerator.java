package com.example.ecommerce_orderservice.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderNumberGenerator {

    private static OrderNumberGenerator instance = null;
//    private final AtomicInteger counter = new AtomicInteger(0);
//    private String currentDate = getToday();
//
//    public synchronized String generateOrderNumber() {
//
//        String today = getToday();
//
//        if (!today.equals(currentDate)) {
//            counter.set(0);
//            currentDate = today;
//        }
//
//        int number = counter.incrementAndGet();
//
//        return "ORD-" + today + "-" + String.format("%04d", number);
//    }
//
//    private String getToday() {
//        return LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
//    }

    private OrderNumberGenerator () {

    }

    public static OrderNumberGenerator getInstance() {

        if (instance == null) {
            synchronized (OrderNumberGenerator.class) {
                if (instance == null) {
                    instance = new OrderNumberGenerator();
                }
            }
        }

        return instance;
    }

    public String generateOrderNumber() {
        return "ORD-" + System.currentTimeMillis();
    }

}