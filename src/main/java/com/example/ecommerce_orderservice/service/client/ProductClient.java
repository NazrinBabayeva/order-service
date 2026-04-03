package com.example.ecommerce_orderservice.service.client;

import com.example.ecommerce_orderservice.model.dto.response.ProductResponseDto;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;


@FeignClient(name = "product-service", url = "http://localhost:8082")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductResponseDto getProductById(@PathVariable("id") Long id);

    @GetMapping("/api/products")
    List<ProductResponseDto> getAllProducts();
}

