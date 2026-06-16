package com.app.ecomerce_api.dto;

import com.app.ecomerce_api.model.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotNull
    Long id;
    @Size(min = 3, max = 100)
    private String firstName;
    @Size(min = 3, max = 100)
    private String lastName;
    @Email
    private String email;
    private int phone;
    private UserRole role;
    @Valid
    private AddressDTO address;
}
