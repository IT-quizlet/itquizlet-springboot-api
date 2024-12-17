package com.example.itquizletspringbootapi.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class UserDto {

    String username;
    String email;
    String avatarUrl;
    LocalDateTime createdAt;

}
