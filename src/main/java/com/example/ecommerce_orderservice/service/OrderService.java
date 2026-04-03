package com.example.ecommerce_orderservice.service;

import com.example.ecommerce_orderservice.enums.Category;
import com.example.ecommerce_orderservice.enums.OrderStatus;
import com.example.ecommerce_orderservice.event.OrderCreatedEvent;
import com.example.ecommerce_orderservice.model.dto.request.OrderCreateRequest;
import com.example.ecommerce_orderservice.model.dto.response.OrderResponseDto;
import com.example.ecommerce_orderservice.model.dto.response.ProductResponseDto;
import com.example.ecommerce_orderservice.model.entity.Order;
import com.example.ecommerce_orderservice.repository.OrderRepository;
import com.example.ecommerce_orderservice.service.client.ProductClient;
import com.example.ecommerce_orderservice.util.OrderNumberGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer eventProducer;
    private final EmailNotificationService emailNotificationService;
    private final ProductClient productClient;

    private final ElectronicsDiscountStrategy electronicsStrategy;
    private final ClothesDiscountStrategy clothesStrategy;


    public OrderResponseDto createOrderSwagger(OrderCreateRequest request) {

        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(1L);
        response.setProductId(request.getProductId());
        response.setQuantity(request.getQuantity());
        response.setPrice(request.getPrice());
        response.setEmail(request.getEmail());
        response.setStatus(OrderStatus.CREATED);


        return response;
    }

    public double calculatePrice(double price, Category category){

        DiscountStrategy strategy =
                DiscountStrategyFactory.getStrategy(category);

        return strategy.applyDiscount(price);
    }

    public String createOrder() {

        OrderNumberGenerator orderNumberGenerator =
                OrderNumberGenerator.getInstance();

        OrderNumberGenerator orderNumberGeneratorSecond =
                OrderNumberGenerator.getInstance();
        if (orderNumberGenerator == orderNumberGeneratorSecond) {
            System.out.printf("Singleton worls roperly");
        }

        String orderNumber = orderNumberGenerator.generateOrderNumber();

        return "Order created with number: " + orderNumber;
    }

    @Transactional
    public Order createOrder(OrderCreateRequest request) {

        ProductResponseDto product = productClient.getProductById(request.getProductId());

        if (product == null || !Boolean.TRUE.equals(product.getActive())) {
            throw new RuntimeException("Product not found");
        }

        if (product.getCount() < request.getQuantity()) {
            throw new RuntimeException("Stock not enough");
        }

        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .email(request.getEmail())
                .build();

        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getOrderId(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                savedOrder.getPrice(),
                savedOrder.getEmail()
        );

        eventProducer.sendOrderCreatedEvent(event);

        emailNotificationService.sendOrderCreatedEmail(savedOrder.getOrderId());

        return savedOrder;
    }
}
