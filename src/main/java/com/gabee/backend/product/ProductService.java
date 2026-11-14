package com.gabee.backend.product;

import com.gabee.backend.product.dto.ProductDTO;
import com.gabee.backend.product.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> findAll(boolean isActive);

    ProductResponseDTO findById(Long id);

    Product findProductById(Long id);

    ProductResponseDTO insert(ProductDTO obj);

    ProductResponseDTO update(Long id, ProductDTO dto);

    void delete(Long id);

    void restore(Long id);
}
