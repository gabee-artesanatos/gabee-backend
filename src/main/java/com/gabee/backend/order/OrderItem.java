package com.gabee.backend.order;

import com.gabee.backend.exceptions.BusinessRuleException;
import com.gabee.backend.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Integer quantity;

    @NotBlank
    private String productName;

    @NotNull
    @Positive
    private BigDecimal productPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, String productName, BigDecimal productPrice, Product product) {
        validateQuantity(quantity);
        this.quantity = quantity;
        this.productName = productName;
        this.productPrice = productPrice;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        validateQuantity(quantity);

        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if (product == null) {
            throw new BusinessRuleException("Informe um produto válido!");
        }

        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void sumItem(int quantity) {
        validateQuantity(quantity);

        this.quantity += quantity;
    }

    public void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new BusinessRuleException("Informe uma quantidade valida!");
        }
    }
}
