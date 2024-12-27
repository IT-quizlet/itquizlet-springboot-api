package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.user.UserDto;
import com.example.itquizletspringbootapi.dto.user.UserUpdateDto;
import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import com.example.itquizletspringbootapi.service.UserService;
import com.example.itquizletspringbootapi.service.mapper.UserMapper;
import com.example.itquizletspringbootapi.web.decorators.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) throws BadRequestException {
        UserEntity user = userService.getUserById(id);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(
            @RequestBody UserUpdateDto userUpdateDTO,
            @CurrentUser UserEntity user
    ) throws BadRequestException {
        UserEntity updatedUser = userService.updateUser(user.getId(), userUpdateDTO);
        return ResponseEntity.ok(userMapper.toDto(updatedUser));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(@CurrentUser UserEntity user) throws BadRequestException {
        userService.deleteUser(user.getId());
        return ResponseEntity.noContent().build();
    }

}
