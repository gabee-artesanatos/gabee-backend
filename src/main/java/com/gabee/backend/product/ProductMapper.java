package com.gabee.backend.product;

import com.gabee.backend.product.dto.ProductDTO;
import com.gabee.backend.product.dto.ProductResponseDTO;

public class ProductMapper {

    public ProductMapper() {
    }

    //DTO de criação -> Entidade
    public static Product toEntity(ProductDTO dto) {

        Product product = new Product();

        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setDescription(dto.description());
        product.setHeightCm(dto.heightCm());
        product.setWidthCm(dto.widthCm());

        return product;
    }

    // Entidade -> DTO de resposta
    public static ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getHeightCm(),
                product.getWidthCm(),
                product.isActive()
        );
    }

    // DTO de atualização -> Entidade existente
    public static void updateProduct(ProductDTO updateProduct, Product product) {
        product.setName(updateProduct.name());
        product.setPrice(updateProduct.price());
        product.setDescription(updateProduct.description());
        product.setHeightCm(updateProduct.heightCm());
        product.setWidthCm(updateProduct.widthCm());
    }
}