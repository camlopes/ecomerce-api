package com.app.ecomerce_api.model;

import com.app.ecomerce_api.dto.AddressDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    public Address(AddressDTO addressDTO) {
        this.street = addressDTO.getStreet();
        this.city = addressDTO.getCity();
        this.state = addressDTO.getState();
        this.country = addressDTO.getCountry();
        this.zipCode = addressDTO.getZipCode();
    }

    public void updateAddress(@Valid AddressDTO address) {
        if (address.getStreet() != null) {
            this.street = address.getStreet();
        }
        if (address.getCity() != null) {
            this.city = address.getCity();
        }
        if (address.getState() != null) {
            this.state = address.getState();
        }
        if (address.getCountry() != null) {
            this.country = address.getCountry();
        }
        if (address.getZipCode() != null) {
            this.zipCode = address.getZipCode();
        }
    }
}
