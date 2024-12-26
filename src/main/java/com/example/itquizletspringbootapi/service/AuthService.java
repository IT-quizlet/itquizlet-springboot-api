package com.example.itquizletspringbootapi.service;

import com.example.itquizletspringbootapi.dto.user.*;

public interface AuthService {

    AuthenticationDto register(UserRegisterDto request);
    AuthenticationDto login(UserLoginDto request);

}
