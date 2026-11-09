package com.gabee.backend.order.dto;

import com.gabee.backend.customer.Customer;
import com.gabee.backend.order.OrderItem;
import com.gabee.backend.order.OrderStatus;

import java.util.List;

public record OrderResponseDTO(
        long id,
        OrderStatus status,
        List<OrderItem> items,
        Customer customer
) {
}
