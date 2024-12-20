package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.user.UserDto;
import com.example.itquizletspringbootapi.dto.user.UserRegisterDto;
import com.example.itquizletspringbootapi.dto.user.UserUpdateDto;
import com.example.itquizletspringbootapi.repository.UserRepository;
import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import com.example.itquizletspringbootapi.service.UserService;
import com.example.itquizletspringbootapi.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto registerUser(UserRegisterDto userRegisterDto) {

        Optional<UserEntity> existingUsername = userRepository.findByUsername(userRegisterDto.getUsername());
        if(existingUsername.isPresent()) {
            log.error("Username '{}' is already taken.", userRegisterDto.getUsername());
            throw new IllegalArgumentException("Username is already taken.");
        }

        Optional<UserEntity> existingEmail = userRepository.findByUsername(userRegisterDto.getEmail());
        if(existingEmail.isPresent()) {
            log.error("Email '{}' is already taken.", userRegisterDto.getUsername());
            throw new IllegalArgumentException("Email is already taken.");
        }

        UserEntity userEntity = userMapper.toEntity(userRegisterDto);
        userEntity.setCreatedAt(LocalDateTime.now());

        UserEntity savedUser = userRepository.save(userEntity);

        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        log.info("Retrieved user with username: {}", username);
        return userMapper.toDto(userEntity);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        log.info("Retrieved user with email: {}", email);
        return userMapper.toDto(userEntity);
    }

    @Override
    public UserDto updateUser(UUID userId, UserUpdateDto updatedUserDto) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        userMapper.updateUserFromDto(updatedUserDto, userEntity);
        UserEntity updatedUserEntity = userRepository.save(userEntity);
        log.info("Updated user with ID: {}", userId);
        return userMapper.toDto(updatedUserEntity);
    }

    @Override
    public void deleteUser(UUID userId) {
        if(!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
        log.info("Deleted user with ID: {}", userId);
    }

}
