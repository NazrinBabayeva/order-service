package com.example.ecommerce_orderservice.model.dto.response;

import com.example.ecommerce_orderservice.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
@Data

@Schema(description = "Order response")
public class OrderResponseDto {

    @Schema(description = "Order ID", example = "101")
    private Long orderId;
    @Schema(description = "Product ID", example = "123")
    private Long productId;
    @Schema(description = "Quantity", example = "2")
    private Long quantity;
    @Schema(description = "Price", example = "300.0")
    private BigDecimal price;
    private String email;
    private OrderStatus status;
}
