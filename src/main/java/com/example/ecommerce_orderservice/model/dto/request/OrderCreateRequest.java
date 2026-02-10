package com.example.ecommerce_orderservice.model.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreateRequest {
    private Long productId;
    private Long quantity;
    private BigDecimal price;
}
