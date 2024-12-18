package com.example.itquizletspringbootapi.service.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    QuizEntity toEntity(QuizDTO dto);
    QuizDTO toDTO(QuizEntity entity);

}
