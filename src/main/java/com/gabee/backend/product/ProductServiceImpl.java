package com.gabee.backend.product;

import com.gabee.backend.exceptions.ResourceNotFoundException;
import com.gabee.backend.product.dto.ProductDTO;
import com.gabee.backend.product.dto.ProductResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public List<ProductResponseDTO> findAll(boolean isActive) {

        return repository.findByActive(isActive)
                .stream()
                .map(product -> ProductMapper.toResponseDTO(product))
                .toList();
    }

    public ProductResponseDTO findById(Long id) {
        Product product = findProductById(id);

        return ProductMapper.toResponseDTO(product);
    }

    public Product findProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public ProductResponseDTO insert(ProductDTO obj) {
        Product product = ProductMapper.toEntity(obj);

        Product savedProduct = repository.save(product);

        return ProductMapper.toResponseDTO(savedProduct);
    }

    public ProductResponseDTO update(Long id, ProductDTO dto) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        ProductMapper.updateProduct(dto, product);

        Product productUpdated = repository.save(product);

        return ProductMapper.toResponseDTO(productUpdated);
    }

    public void delete(Long id) {
        Product deletedProduct = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        deletedProduct.setActive(false);

        repository.save(deletedProduct);
    }

    public void restore(Long id) {
        Product restoredProduct = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        restoredProduct.setActive(true);

        repository.save(restoredProduct);
    }
}
