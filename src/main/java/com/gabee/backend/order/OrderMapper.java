package com.gabee.backend.order;

import com.gabee.backend.order.dto.OrderItemResponseDTO;
import com.gabee.backend.order.dto.OrderResponseDTO;

public class OrderMapper {
    public OrderMapper() {
    }

    // Entidade -> DTO de resposta
    public static OrderResponseDTO toResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getStatus(),
                order.getItems(),
                order.getCustomer()
        );
    }

    public static OrderItemResponseDTO toItemResponseDTO(OrderItem item) {
        return new OrderItemResponseDTO(
                item.getProduct().getId(),
                item.getQuantity()
        );
    }
}
