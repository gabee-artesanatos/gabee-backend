package com.gabee.backend.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderDTO(
        @NotNull
        Long customerId,

        @NotEmpty
        @Valid
        List<OrderItemDTO> items
) {
}
