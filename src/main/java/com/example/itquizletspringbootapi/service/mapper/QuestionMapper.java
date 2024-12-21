package com.example.itquizletspringbootapi.service.mapper;

import com.example.itquizletspringbootapi.repository.entity.QuestionEntity;
import com.example.itquizletspringbootapi.dto.question.QuestionDto;
import com.example.itquizletspringbootapi.dto.question.QuestionCreateDto;
import com.example.itquizletspringbootapi.dto.question.QuestionUpdateDto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionEntity toEntity(QuestionDto dto);

    QuestionEntity toEntity(QuestionCreateDto dto);

    QuestionDto toDTO(QuestionEntity entity);

    QuestionEntity updateEntityFromDto(QuestionUpdateDto updateDto, @MappingTarget QuestionEntity existingEntity);

}
