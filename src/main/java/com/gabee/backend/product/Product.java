package com.gabee.backend.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    private String description;

    @NotNull
    @Positive
    @Column(name = "height_cm")
    private BigDecimal heightCm;

    @NotNull
    @Positive
    @Column(name = "width_cm")
    private BigDecimal widthCm;

    @Column(nullable = false)
    private boolean active = true;

    public Product() {
    }

    public Product(String name, BigDecimal price, String description, BigDecimal heightCm, BigDecimal widthCm) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.heightCm = heightCm;
        this.widthCm = widthCm;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(BigDecimal heightCm) {
        this.heightCm = heightCm;
    }

    public BigDecimal getWidthCm() {
        return widthCm;
    }

    public void setWidthCm(BigDecimal widthCm) {
        this.widthCm = widthCm;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}