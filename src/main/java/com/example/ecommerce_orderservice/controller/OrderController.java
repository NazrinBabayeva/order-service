package com.example.ecommerce_orderservice.controller;

import com.example.ecommerce_orderservice.enums.Category;
import com.example.ecommerce_orderservice.mapper.OrderMapper;
import com.example.ecommerce_orderservice.model.dto.request.OrderCreateRequest;
import com.example.ecommerce_orderservice.model.dto.response.OrderResponseDto;
import com.example.ecommerce_orderservice.model.entity.Order;
import com.example.ecommerce_orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Tag(name = "Order API", description = "Order management endpoints")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @PostMapping("/swagger")
    @Operation(
            summary = "Create new order (Swagger test)",
            description = "Creates a new order using swagger-specific endpoint"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public OrderResponseDto createOrder(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Order creation request",
                    required = true
            )
            @RequestBody OrderCreateRequest request) {

        return orderService.createOrderSwagger(request);
    }

    @PostMapping
    @Operation(
            summary = "Create order",
            description = "Creates a new order and returns OrderResponseDto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    public ResponseEntity<OrderResponseDto> create(
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Order creation request with validation",
                    required = true
            )
            @RequestBody OrderCreateRequest request) {

        Order order = orderService.createOrder(request);
        OrderResponseDto response = orderMapper.toDto(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/order")
    @Operation(
            summary = "Create simple order",
            description = "Creates order without request body (demo endpoint)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public String createOrder() {
        return orderService.createOrder();
    }

    @GetMapping("/{category}/price")
    @Operation(
            summary = "Calculate price by category",
            description = "Calculates final price based on category"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price calculated"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters")
    })
    public double calculate(
            @Parameter(description = "Base price", example = "100")
            @RequestParam double price,

            @Parameter(description = "Product category", example = "ELECTRONICS")
            @PathVariable Category category) {

        return orderService.calculatePrice(price, category);
    }

    @GetMapping("/{category}/discount")
    @Operation(
            summary = "Get discount by category",
            description = "Returns discounted price based on category"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Discount calculated"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters")
    })
    public double getDiscount(

            @Parameter(description = "Base price", example = "100")
            @RequestParam double price,

            @Parameter(description = "Product category", example = "ELECTRONICS")
            @RequestParam Category category
    ) {
        return orderService.calculatePrice(price, category);
    }
}