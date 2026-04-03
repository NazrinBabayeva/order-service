package com.example.ecommerce_orderservice.mapper;

import com.example.ecommerce_orderservice.model.dto.response.OrderResponseDto;
import com.example.ecommerce_orderservice.model.dto.response.ProductResponseDto;
import com.example.ecommerce_orderservice.model.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    ProductResponseDto toProductResponseDto(Order order);

    OrderResponseDto toDto(Order order);
}
