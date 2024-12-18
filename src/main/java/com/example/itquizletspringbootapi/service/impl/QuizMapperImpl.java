package com.example.itquizletspringbootapi.service.mapper;

import com.example.itquizletspringbootapi.dto.QuizDTO;
import com.example.itquizletspringbootapi.entity.QuizEntity;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper implements QuizMapper {

    public QuizEntity toEntity(QuizDTO dto) {
        QuizEntity entity = new QuizEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        // Логіка для мапінгу питань, якщо потрібно
        return entity;
    }

    public QuizDTO toDTO(QuizEntity entity) {
        QuizDTO dto = new QuizDTO();
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        // Логіка для мапінгу питань, якщо потрібно
        return dto;
    }
}
