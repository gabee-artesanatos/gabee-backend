package com.gabee.backend.order;

import com.gabee.backend.order.dto.OrderDTO;
import com.gabee.backend.order.dto.OrderResponseDTO;
import com.gabee.backend.order.dto.OrderUpdateDTO;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> findAll();

    OrderResponseDTO findById(Long id);

    Order findOrderById(Long id);

    OrderResponseDTO insert(OrderDTO obj);

    OrderResponseDTO update(Long id, OrderUpdateDTO obj);

    void delete(Long id);

    void restore(Long id);
}
