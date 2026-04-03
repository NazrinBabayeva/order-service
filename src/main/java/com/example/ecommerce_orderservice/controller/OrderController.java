package com.example.ecommerce_orderservice.controller;

import com.example.ecommerce_orderservice.enums.Category;
import com.example.ecommerce_orderservice.mapper.OrderMapper;
import com.example.ecommerce_orderservice.model.dto.request.OrderCreateRequest;
import com.example.ecommerce_orderservice.model.dto.response.OrderResponseDto;
import com.example.ecommerce_orderservice.model.entity.Order;
import com.example.ecommerce_orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {


    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @PostMapping("/swagger")
    @Operation(summary = "Create new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public OrderResponseDto createOrder(@RequestBody OrderCreateRequest request) {
        return orderService.createOrderSwagger(request);
    }


    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody @Valid OrderCreateRequest request) {

        Order order = orderService.createOrder(request);

        OrderResponseDto response = orderMapper.toDto(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/order")
    public String createOrder() {
        return orderService.createOrder();
    }

    @GetMapping("/{category}/price")
    public double calculate(@RequestParam double price,
                            @PathVariable Category category) {

        return orderService.calculatePrice(price, category);
    }


    @GetMapping("/{category}/discount")
    public double getDiscount(
            @RequestParam double price,
            @RequestParam Category category
    ) {
        return orderService.calculatePrice(price, category);
    }
}
