package com.example.ecommerce_orderservice.service;

import com.example.ecommerce_orderservice.enums.OrderStatus;
import com.example.ecommerce_orderservice.event.OrderCreatedEvent;
import com.example.ecommerce_orderservice.model.dto.request.OrderCreateRequest;
import com.example.ecommerce_orderservice.model.entity.Order;
import com.example.ecommerce_orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer eventProducer;
    private final EmailNotificationService emailNotificationService;

    public Order createOrder(OrderCreateRequest request) {

        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getOrderId(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                savedOrder.getPrice()
        );

        eventProducer.sendOrderCreatedEvent(event);

        emailNotificationService.sendOrderCreatedEmail(savedOrder.getOrderId());

        return savedOrder;
    }
}
