package com.gabee.backend.customer;

import jakarta.validation.constraints.NotBlank;

public class Adress {
    @NotBlank
    private String cep;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String complement;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    public Adress() {
    }


}
