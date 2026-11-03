package com.gabee.backend.customer.dto;

import com.gabee.backend.customer.Address;

import java.time.LocalDate;

public record CustomerResponseDTO(
        long id,
        String name,
        String cpf,
        String phone,
        String email,
        LocalDate birthday,
        String gender,
        Address address) {
}
