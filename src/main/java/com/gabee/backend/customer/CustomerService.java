package com.gabee.backend.customer;

import com.gabee.backend.customer.dto.CustomerDTO;
import com.gabee.backend.customer.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDTO> findAll(boolean isActive);

    CustomerResponseDTO findById(Long id);

    Customer findCustomerById(Long id);

    CustomerResponseDTO insert(CustomerDTO dto);

    CustomerResponseDTO update(Long id, CustomerDTO dto);

    void delete(Long id);

    void restore(Long id);
}
