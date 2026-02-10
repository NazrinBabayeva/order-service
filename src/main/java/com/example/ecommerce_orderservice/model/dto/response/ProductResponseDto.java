package com.example.ecommerce_orderservice.model.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private Long count;
    private Boolean active;
    private BigDecimal price;

}
