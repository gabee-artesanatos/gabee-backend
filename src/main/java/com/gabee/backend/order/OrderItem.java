package com.gabee.backend.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabee.backend.exceptions.BusinessRuleException;
import com.gabee.backend.photo.Photo;
import com.gabee.backend.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Integer quantity;

    private String photoCaption;

    @NotBlank
    private String productName;

    @NotNull
    @Positive
    private BigDecimal productPrice;

    @NotNull
    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST)
    @OrderColumn(name = "order_photo")
    private List<Photo> photos = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Integer quantity, String photoCaption, String productName, BigDecimal productPrice, Product product, List<Photo> photos) {
        validateQuantity(quantity);

        this.quantity = quantity;
        this.photoCaption = photoCaption;
        this.productName = productName;
        this.productPrice = productPrice;
        this.product = product;

        setPhotos(photos);
    }

    public void setQuantity(Integer quantity) {
        validateQuantity(quantity);

        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        if (product == null) {
            throw new BusinessRuleException("Informe um produto válido!");
        }

        this.product = product;
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

    public void validateQuantityPhotos(List<Photo> quantityPhotos) {
        if (quantityPhotos.size() != product.getQuantityPhotos() || product.getQuantityPhotos() <= 0) {
            throw new BusinessRuleException("Insira a quantidade de fotos necessárias para este produto!");
        }
    }

    public void setPhotos(List<Photo> listPhoto) {
        validateQuantityPhotos(listPhoto);

        photos.clear();
        photos.addAll(listPhoto);

        for (Photo photo : listPhoto) {
            photo.setItem(this);
        }
    }
}
