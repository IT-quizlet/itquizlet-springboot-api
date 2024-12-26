package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.user.AuthenticationDto;
import com.example.itquizletspringbootapi.dto.user.UserLoginDto;
import com.example.itquizletspringbootapi.dto.user.UserRegisterDto;
import com.example.itquizletspringbootapi.service.impl.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
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
}