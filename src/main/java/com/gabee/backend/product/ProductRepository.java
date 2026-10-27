package com.gabee.backend.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Conexão com o banco sempre vai ser uma interface
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActive(boolean active);
}
