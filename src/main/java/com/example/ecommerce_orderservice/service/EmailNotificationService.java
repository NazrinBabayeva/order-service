package com.example.ecommerce_orderservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailNotificationService {

    @Async
    public void sendOrderCreatedEmail(Long orderId) {
        log.info("Email sending started for orderId={}", orderId);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        log.info("Email sent successfully for orderId={}", orderId);
    }

}
