package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.user.AuthenticationDto;
import com.example.itquizletspringbootapi.dto.user.UserDto;
import com.example.itquizletspringbootapi.dto.user.UserLoginDto;
import com.example.itquizletspringbootapi.dto.user.UserRegisterDto;
import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import com.example.itquizletspringbootapi.service.impl.AuthServiceImpl;
import com.example.itquizletspringbootapi.service.mapper.UserMapper;
import com.example.itquizletspringbootapi.web.decorators.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthServiceImpl authService;
    private final UserMapper userMapper;

    public AuthController(AuthServiceImpl authService, UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationDto> register (
            @RequestBody UserRegisterDto request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDto> login (
        @RequestBody UserLoginDto request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public UserDto getCurrentUser (@CurrentUser UserEntity user) {
        return userMapper.toDto(user);
    }
}