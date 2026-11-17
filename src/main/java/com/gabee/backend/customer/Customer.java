package com.gabee.backend.customer;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String phone;

    @NotBlank
    @Email
    private String email;

    private LocalDate birthday;

    private String gender;

    @Embedded
    @Valid
    @NotNull
    private Address address;

    @Column(nullable = false)
    private boolean active = true;
}
