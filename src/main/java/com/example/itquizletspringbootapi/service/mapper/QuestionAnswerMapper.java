package com.example.itquizletspringbootapi.service.mapper;

import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerCreateDto;
import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerDto;
import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerUpdateDto;
import com.example.itquizletspringbootapi.repository.entity.QuestionAnswerEntity;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuestionAnswerMapper {

    QuestionAnswerEntity toEntity(QuestionAnswerDto dto);

    QuestionAnswerEntity toEntity(QuestionAnswerCreateDto dto);

    QuestionAnswerDto toDto(QuestionAnswerEntity entity);

    QuestionAnswerEntity updateEntityFromDto(QuestionAnswerUpdateDto updateDto, @MappingTarget QuestionAnswerEntity existingEntity);

}
