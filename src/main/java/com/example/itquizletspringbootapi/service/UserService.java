package com.example.itquizletspringbootapi.service;

import com.example.itquizletspringbootapi.dto.UserRegisterDto;
import com.example.itquizletspringbootapi.dto.UserDto;
import com.example.itquizletspringbootapi.dto.UserUpdateDto;

import java.util.UUID;

public interface UserService {

    UserDto registerUser(UserRegisterDto user);
    UserDto getUserByUsername(String username);
    UserDto getUserByEmail(String email);
    UserDto updateUser(UUID userId, UserUpdateDto updatedUser);
    void deleteUser(UUID userId);

}
