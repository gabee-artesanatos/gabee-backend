package com.gabee.backend.order.dto;

public record OrderItemResponseDTO(
        Long productId,
        Integer quantity
) {
}
