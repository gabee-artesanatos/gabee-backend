package com.gabee.backend.order.dto;

import com.gabee.backend.order.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record OrderUpdateDTO(
        @NotNull
        @Enumerated(EnumType.STRING)
        OrderStatus status
) {
}
