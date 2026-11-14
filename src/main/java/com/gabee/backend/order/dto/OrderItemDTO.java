package com.gabee.backend.order.dto;

import com.gabee.backend.photo.Photo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record OrderItemDTO(
        @NotNull
        Long productId,

        @NotNull
        @Positive
        Integer quantity,

        String photoCaption,

        @NotEmpty
        List<Photo> photos
) {
}
