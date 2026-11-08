package com.gabee.backend.order;

import com.gabee.backend.order.dto.OrderDTO;
import com.gabee.backend.order.dto.OrderResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        List<OrderResponseDTO> orders = service.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id) {
        OrderResponseDTO order = service.findById(id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> insert(@Valid @RequestBody OrderDTO obj) {
        OrderResponseDTO response = service.insert(obj);
        return ResponseEntity.status(201).body(response);
    }
}
