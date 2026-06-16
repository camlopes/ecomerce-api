package com.app.ecomerce_api.service;

import com.app.ecomerce_api.dto.AddressDTO;
import com.app.ecomerce_api.dto.UpdateUserRequest;
import com.app.ecomerce_api.dto.UserResponse;
import com.app.ecomerce_api.dto.UserRequest;
import com.app.ecomerce_api.model.User;
import com.app.ecomerce_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> fetchAllUsers(){
        return userRepository.findAll().stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
    }

    private UserResponse convertToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId().toString());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(user.getAddress().getStreet());
        addressDTO.setCity(user.getAddress().getCity());
        addressDTO.setState(user.getAddress().getState());
        addressDTO.setCountry(user.getAddress().getCountry());
        addressDTO.setZipCode(user.getAddress().getZipCode());
        response.setAddress(addressDTO);
        return response;
    }

    public void addUser(UserRequest userRequest){
        User user = new User(userRequest);
        userRepository.save(user);
    }

    public Optional<UserResponse> fetchUser(Long id) {
        return userRepository.findById(id).map(this::convertToUserResponse);
    }

    public boolean updateUser(Long id, UpdateUserRequest updateUserRequest) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isEmpty()) {
            return false;
        } else {
            findUser.get().updateUser(updateUserRequest);
            userRepository.save(findUser.get());
            return true;
        }
    }
}