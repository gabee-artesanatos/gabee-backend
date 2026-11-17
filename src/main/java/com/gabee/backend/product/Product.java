package com.gabee.backend.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}