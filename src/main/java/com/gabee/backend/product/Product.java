package com.gabee.backend.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
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

    @NotNull
    @PositiveOrZero
    private Integer quantityPhotos;

    @Column(nullable = false)
    private boolean magnet = false;

    @Column(nullable = false)
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeightCm(BigDecimal heightCm) {
        this.heightCm = heightCm;
    }

    public void setWidthCm(BigDecimal widthCm) {
        this.widthCm = widthCm;
    }

    public void setQuantityPhotos(Integer quantityPhotos) {
        this.quantityPhotos = quantityPhotos;
    }

    public void setMagnet(boolean magnet) {
        this.magnet = magnet;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}