package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.user.UserDto;
import com.example.itquizletspringbootapi.dto.user.UserRegisterDto;
import com.example.itquizletspringbootapi.dto.user.UserUpdateDto;
import com.example.itquizletspringbootapi.repository.UserRepository;
import com.example.itquizletspringbootapi.service.UserService;
import com.example.itquizletspringbootapi.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto registerUser(UserRegisterDto user) {
        return null;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto updateUser(UUID userId, UserUpdateDto updatedUser) {
        return null;
    }

    @Override
    public void deleteUser(UUID userId) {

    }

}
