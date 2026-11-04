package com.gabee.backend.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByActive(boolean active);

    Boolean existsByCpf(String cpf);

    Boolean existsByCpfAndIdNot(String cpf, Long id);

    Boolean existsByEmail(String email);

    Boolean existsByEmailAndIdNot(String email, Long id);
}
