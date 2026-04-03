package com.example.ecommerce_orderservice.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.math.BigDecimal;

@Data

@Schema(description = "Order create request")
public class OrderCreateRequest {
    @Schema(description = "Product ID", example = "1")
    private Long productId;
    @Schema(description = "Quantity", example = "2")
    private Long quantity;
    @Schema(description = "Price", example = "300.0")
    private BigDecimal price;
    @Email(message = "Mail is wrong")
    private String email;
}
