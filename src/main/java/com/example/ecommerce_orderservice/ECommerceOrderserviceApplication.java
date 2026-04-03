package com.example.ecommerce_orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
@EnableCaching
@EnableKafka
@EnableFeignClients(basePackages =
        "com.example.ecommerce_orderservice.service.client")
public class ECommerceOrderserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceOrderserviceApplication.class, args);
    }

}
