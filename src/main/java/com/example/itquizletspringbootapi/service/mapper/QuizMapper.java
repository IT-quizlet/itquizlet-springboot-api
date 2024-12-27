package com.example.itquizletspringbootapi.service.mapper;

import com.example.itquizletspringbootapi.repository.entity.QuizEntity;
import com.example.itquizletspringbootapi.dto.quiz.QuizCreateDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizUpdateDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizDto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    QuizEntity toEntity(QuizDto  dto);
    QuizEntity toEntity(QuizCreateDto createDto);
    QuizDto toDTO(QuizEntity entity);
    QuizEntity updateEntityFromDto(QuizUpdateDto updateDto, @MappingTarget QuizEntity existingEntity);

}
