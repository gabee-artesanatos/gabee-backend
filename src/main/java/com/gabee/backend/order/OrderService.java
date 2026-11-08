package com.gabee.backend.order;

import com.gabee.backend.customer.Customer;
import com.gabee.backend.customer.CustomerRepository;
import com.gabee.backend.exceptions.ResourceNotFoundException;
import com.gabee.backend.order.dto.OrderDTO;
import com.gabee.backend.order.dto.OrderItemDTO;
import com.gabee.backend.order.dto.OrderResponseDTO;
import com.gabee.backend.product.Product;
import com.gabee.backend.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository repository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public List<OrderResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(order -> OrderMapper.toResponseDTO(order))
                .toList();
    }

    public OrderResponseDTO findById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return OrderMapper.toResponseDTO(order);
    }

    // Essa anotação serve que para se algum dos passo falhar, cancele tudo e não salve nada
    @Transactional
    public OrderResponseDTO insert(OrderDTO obj) {
        Customer customer = customerRepository.findById(obj.customerId())
                .orElseThrow(() -> new ResourceNotFoundException(obj.customerId()));

        Order order = new Order(customer);

        for (OrderItemDTO orderItem : obj.items()) {
            Long productId = orderItem.productId();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException(productId));

            Integer productQuantity = orderItem.quantity();

            OrderItem savedItem = new OrderItem(productQuantity, product.getName(), product.getPrice(), product);

            order.addItem(savedItem);
        }

        repository.save(order);

        return OrderMapper.toResponseDTO(order);
    }
}
