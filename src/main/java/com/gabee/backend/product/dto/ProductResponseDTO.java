package com.gabee.backend.product.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        BigDecimal price,
        String description,
        BigDecimal heightCm,
        BigDecimal widthCm,
        boolean active) {
}
