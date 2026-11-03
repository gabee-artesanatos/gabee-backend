package com.gabee.backend.customer;

import com.gabee.backend.customer.dto.CustomerDTO;
import com.gabee.backend.customer.dto.CustomerResponseDTO;

public class CustomerMapper {
    public CustomerMapper() {
    }

    public static Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();

        customer.setName(dto.name());
        customer.setCpf(dto.cpf());
        customer.setPhone(dto.phone());
        customer.setEmail(dto.email());
        customer.setBirthday(dto.birthday());
        customer.setGender(dto.gender());
        customer.setAddress(dto.address());

        return customer;
    }

    public static CustomerResponseDTO toResponseDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getCpf(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getBirthday(),
                customer.getGender(),
                customer.getAddress()
        );
    }

    public static void updateCustomer(CustomerDTO updateCustomer, Customer customer) {
        customer.setName(updateCustomer.name());
        customer.setCpf(updateCustomer.cpf());
        customer.setPhone(updateCustomer.phone());
        customer.setEmail(updateCustomer.email());
        customer.setBirthday(updateCustomer.birthday());
        customer.setGender(updateCustomer.gender());
        customer.setAddress(updateCustomer.address());
    }
}