package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.user.UserUpdateDto;
import com.example.itquizletspringbootapi.repository.UserRepository;
import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import com.example.itquizletspringbootapi.service.UserService;
import com.example.itquizletspringbootapi.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserEntity getUserById(UUID id) throws BadRequestException {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found with id: " + id));
    }

    @Override
    public UserEntity updateUser(UUID id, UserUpdateDto updatedUserDto) throws BadRequestException {
        UserEntity userEntity = this.getUserById(id);

        UserEntity user = userMapper.updateUserFromDto(updatedUserDto, userEntity);
        userRepository.save(user);

        return this.getUserById(id);
    }

    @Override
    public void deleteUser(UUID id) throws BadRequestException {

        if (!userRepository.existsById(id)) {
            throw new BadRequestException("User not found with Id: " + id);
        }

        userRepository.deleteById(id);
    }
}
