package com.gabee.backend.photo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabee.backend.order.OrderItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "photos")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
