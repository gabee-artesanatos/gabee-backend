package com.gabee.backend.customer;

import com.gabee.backend.customer.dto.CustomerDTO;
import com.gabee.backend.customer.dto.CustomerResponseDTO;
import com.gabee.backend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerResponseDTO> findAll(boolean isActive) {
        return repository.findByActive(isActive)
                .stream()
                .map(customer -> CustomerMapper.toResponseDTO(customer))
                .toList();
    }

    public CustomerResponseDTO findById(Long id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        return CustomerMapper.toResponseDTO(customer);
    }

    public CustomerResponseDTO insert(CustomerDTO dto) {
        Customer customer = CustomerMapper.toEntity(dto);

        Customer savedCustomer = repository.save(customer);

        return CustomerMapper.toResponseDTO(savedCustomer);
    }

    public CustomerResponseDTO update(Long id, CustomerDTO dto) {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        CustomerMapper.updateCustomer(dto, customer);

        Customer customerUpdated = repository.save(customer);

        return CustomerMapper.toResponseDTO(customerUpdated);
    }

    public void delete(Long id) {
        Customer deletedCustomer = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        deletedCustomer.setActive(false);

        repository.save(deletedCustomer);
    }

    public void restore(Long id) {
        Customer restoredCustomer = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        restoredCustomer.setActive(true);

        repository.save(restoredCustomer);
    }
}
