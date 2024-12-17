package com.example.itquizletspringbootapi.dto.quiz;

import com.example.itquizletspringbootapi.dto.question.QuestionDto;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Value
@Builder
@Jacksonized
public class QuizDto {

    UUID id;
    String title;
    String description;
    String ownerUsername;
    List<QuestionDto> questions;

}