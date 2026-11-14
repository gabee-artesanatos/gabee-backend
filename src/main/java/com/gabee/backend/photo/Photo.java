package com.gabee.backend.photo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabee.backend.order.OrderItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String photoPath;

    @JsonIgnore
    @NotNull
    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem item;

    public Photo(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setItem(OrderItem item) {
        this.item = item;
    }
}
