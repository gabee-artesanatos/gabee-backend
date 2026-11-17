package com.gabee.backend.order;

import com.gabee.backend.customer.Customer;
import com.gabee.backend.customer.CustomerService;
import com.gabee.backend.exceptions.ResourceNotFoundException;
import com.gabee.backend.order.dto.OrderDTO;
import com.gabee.backend.order.dto.OrderItemDTO;
import com.gabee.backend.order.dto.OrderResponseDTO;
import com.gabee.backend.order.dto.OrderUpdateDTO;
import com.gabee.backend.photo.PhotoService;
import com.gabee.backend.product.Product;
import com.gabee.backend.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private CustomerService customerService;
    private ProductService productService;
    private PhotoService photoService;


    public List<OrderResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(order -> OrderMapper.toResponseDTO(order))
                .toList();
    }

    public OrderResponseDTO findById(Long id) {
        Order order = findOrderById(id);

        return OrderMapper.toResponseDTO(order);
    }

    public Order findOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Essa anotação serve que para se algum dos passo falhar, cancele tudo e não salve nada
    @Transactional
    public OrderResponseDTO insert(@NonNull OrderDTO obj) {
        Customer customer = customerService.findCustomerById(obj.customerId());

        Order order = new Order();

        for (OrderItemDTO orderItem : obj.items()) {
            Long productId = orderItem.productId();

            Product product = productService.findProductById(productId);

            Integer productQuantity = orderItem.quantity();

            OrderItem savedItem = new OrderItem(
                    productQuantity,
                    orderItem.photoCaption(),
                    product.getName(),
                    product.getPrice(),
                    product,
                    orderItem.photos()
            );

            order.addItem(savedItem);
        }

        order.setCustomer(customer);

        repository.save(order);

        return OrderMapper.toResponseDTO(order);
    }

    @Transactional
    public OrderResponseDTO update(Long id, OrderUpdateDTO obj) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        order.setStatus(obj.status());
        repository.save(order);

        return OrderMapper.toResponseDTO(order);
    }

    public void delete(Long id) {
        Order deletedOrder = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        deletedOrder.setActive(false);

        repository.save(deletedOrder);
    }

    public void restore(Long id) {
        Order restoredOrder = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        restoredOrder.setActive(true);

        repository.save(restoredOrder);
    }
}
