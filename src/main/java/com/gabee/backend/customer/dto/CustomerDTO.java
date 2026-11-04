package com.gabee.backend.customer.dto;

import com.gabee.backend.customer.Address;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record CustomerDTO(
        @NotBlank
        String name,

        @NotBlank
        @CPF
        String cpf,

        @NotBlank
        String phone,

        @NotBlank
        String email,

        LocalDate birthday,

        String gender,

        @Embedded
        @Valid
        @NotNull
        Address address) {
}
