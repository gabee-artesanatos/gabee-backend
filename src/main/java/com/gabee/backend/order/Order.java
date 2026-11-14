package com.gabee.backend.order;

import com.gabee.backend.customer.Customer;
import com.gabee.backend.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
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

    @Column(nullable = false)
    private boolean active = true;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addItem(OrderItem item) {
        validateStatus(getStatus());

        if (item.getProduct() == null || item.getQuantity() <= 0) {
            throw new BusinessRuleException("Verifique os itens e quantidades do pedido e tente novamente!");
        }

        items.add(item);
        item.setOrder(this);

//        TODO: Agrupamento de itens do pedido, verificar se vai considerar personalização ou não, no momento não considera
//        Optional<OrderItem> existingOrderItem = items.stream()
//                .filter(orderItem -> orderItem.getProduct().getId().equals(item.getProduct().getId()))
//                .findFirst();
//
//        existingOrderItem.ifPresentOrElse(
//                orderItem -> orderItem.sumItem(item.getQuantity()),
//                () -> {
//                    if (item.getQuantity() > 0) {
//                        items.add(item);
//                        item.setOrder(this);
//                    }
//                }
//        );
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void validateStatus(OrderStatus status) {
        if (status != OrderStatus.PENDING) {
            throw new BusinessRuleException("Só é possível alterar o pedido quando está pendente!");
        }
    }
}
