package com.gabee.backend.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDTO(
        @NotBlank
        String name,

        @NotNull
        @Positive
        BigDecimal price,

        String description,

        @NotNull
        @Positive
        BigDecimal heightCm,

        @NotNull
        @Positive
        BigDecimal widthCm) {
}
