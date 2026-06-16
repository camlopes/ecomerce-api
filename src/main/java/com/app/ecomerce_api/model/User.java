package com.app.ecomerce_api.model;

import com.app.ecomerce_api.dto.UpdateUserRequest;
import com.app.ecomerce_api.dto.UserRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.CUSTOMER;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User(UserRequest userRequest) {
        this.firstName = userRequest.getFirstName();
        this.lastName = userRequest.getLastName();
        this.email = userRequest.getEmail();
        this.phone = userRequest.getPhone();

        if (userRequest.getRole() == null) {
            this.role = UserRole.CUSTOMER;
        } else {
            this.role = userRequest.getRole();
        }

        this.address = new Address(userRequest.getAddress());
    }

    public void updateUser(UpdateUserRequest updateUserRequest) {
        if (updateUserRequest.getFirstName() != null) {
            this.firstName = updateUserRequest.getFirstName();
        }
        if (updateUserRequest.getLastName() != null) {
            this.lastName = updateUserRequest.getLastName();
        }
        if (updateUserRequest.getEmail() != null) {
            this.email = updateUserRequest.getEmail();
        }
        if (updateUserRequest.getPhone() != 0) {
            this.phone = updateUserRequest.getPhone();
        }
        if (updateUserRequest.getRole() != null) {
            this.role = updateUserRequest.getRole();
        }
        if (updateUserRequest.getAddress() != null) {
            this.address.updateAddress(updateUserRequest.getAddress());
        }
    }
}
