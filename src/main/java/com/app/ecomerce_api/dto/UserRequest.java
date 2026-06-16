package com.app.ecomerce_api.dto;

import com.app.ecomerce_api.model.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private int phone;
    private UserRole role;
    @NotNull
    @Valid
    private AddressDTO address;
}
