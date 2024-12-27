package com.example.itquizletspringbootapi.dto.user;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class UserDto {

    String username;
    String avatarUrl;
    String email;

}
