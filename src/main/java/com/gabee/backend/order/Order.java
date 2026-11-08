package com.gabee.backend.order;

import com.gabee.backend.customer.Customer;
import com.gabee.backend.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @NotNull
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> items = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order() {
    }

    public Order(Customer customer) {
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addItem(OrderItem item) {
        validateStatus(getStatus());

        if (item.getProduct() == null || item.getQuantity() <= 0) {
            throw new BusinessRuleException("Verifique os itens e quantidades do pedido e tente novamente!");
        }

        Optional<OrderItem> existingOrderItem = items.stream()
                .filter(i -> i.getProduct().getId().equals(item.getProduct().getId()))
                .findFirst();

        existingOrderItem.ifPresentOrElse(
                i -> i.sumItem(item.getQuantity()),
                () -> {
                    if (item.getQuantity() > 0) {
                        items.add(item);
                        item.setOrder(this);
                    }
                }
        );
    }

    public void removeItem(OrderItem item) {
        validateStatus(getStatus());

        if (item.getOrder() != this) {
            throw new BusinessRuleException("Só é possível excluir um item do próprio pedido");
        }

        boolean removedItem = items.remove(item);

        if (removedItem) {
            item.setOrder(null);
        }
    }

    public void validateStatus(OrderStatus status) {
        if (status != OrderStatus.PENDING) {
            throw new BusinessRuleException("Só é possível alterar o pedido quando está pendente!");
        }
    }
}
