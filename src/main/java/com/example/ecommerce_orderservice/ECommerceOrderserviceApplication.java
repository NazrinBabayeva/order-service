package com.example.ecommerce_orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableCaching
public class ECommerceOrderserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceOrderserviceApplication.class, args);
    }

}
