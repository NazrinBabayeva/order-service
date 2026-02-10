package com.example.ecommerce_orderservice.controller;

import com.example.ecommerce_orderservice.model.dto.request.OrderCreateRequest;
import com.example.ecommerce_orderservice.model.entity.Order;
import com.example.ecommerce_orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order create(@RequestBody OrderCreateRequest request) {
        return orderService.createOrder(request);

    }
}
