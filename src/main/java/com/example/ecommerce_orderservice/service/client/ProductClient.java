package com.example.ecommerce_orderservice.service.client;

import com.example.ecommerce_orderservice.model.dto.response.ProductResponseDto;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


//@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {
    @GetMapping("/products/{id}")
    ProductResponseDto getProductById(@PathVariable("id") Long id);

    @GetMapping("/products")
    List<ProductResponseDto> getAllProducts();
}
