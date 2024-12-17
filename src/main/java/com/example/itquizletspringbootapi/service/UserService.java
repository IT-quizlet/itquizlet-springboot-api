package com.example.itquizletspringbootapi.service;

import com.example.itquizletspringbootapi.dto.user.UserRegisterDto;
import com.example.itquizletspringbootapi.dto.user.UserDto;
import com.example.itquizletspringbootapi.dto.user.UserUpdateDto;

import java.util.UUID;

public interface UserService {

    UserDto registerUser(UserRegisterDto user);
    UserDto getUserByUsername(String username);
    UserDto getUserByEmail(String email);
    UserDto updateUser(UUID userId, UserUpdateDto updatedUser);
    void deleteUser(UUID userId);

}
