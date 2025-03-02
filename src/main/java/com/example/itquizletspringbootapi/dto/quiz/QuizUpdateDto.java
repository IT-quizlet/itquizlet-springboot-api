package com.example.itquizletspringbootapi.dto.quiz;

import com.example.itquizletspringbootapi.repository.entity.Level;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class QuizUpdateDto {
    String title;
    String description;
    Level level;
    List<String> categories;

}
