package com.example.itquizletspringbootapi.service;

import com.example.itquizletspringbootapi.dto.user.UserUpdateDto;
import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import org.apache.coyote.BadRequestException;

import java.util.UUID;

public interface UserService {

    UserEntity getUserById(UUID id) throws BadRequestException;
    UserEntity updateUser(UUID id, UserUpdateDto updatedUser) throws BadRequestException;
    void deleteUser(UUID id) throws BadRequestException;

}
