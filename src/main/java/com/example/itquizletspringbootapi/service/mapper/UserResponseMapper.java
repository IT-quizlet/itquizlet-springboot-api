package com.example.itquizletspringbootapi.service.mapper;

import com.example.itquizletspringbootapi.dto.userresponse.UserResponseCreateDto;
import com.example.itquizletspringbootapi.dto.userresponse.UserResponseDto;
import com.example.itquizletspringbootapi.repository.entity.UserResponseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponseEntity toEntity(UserResponseDto dto);

    UserResponseEntity toEntity(UserResponseCreateDto dto);

    UserResponseDto toDto(UserResponseEntity entity);
}
