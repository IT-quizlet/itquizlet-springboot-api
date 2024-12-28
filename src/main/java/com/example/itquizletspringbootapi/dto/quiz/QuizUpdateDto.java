package com.example.itquizletspringbootapi.dto.quiz;

import com.example.itquizletspringbootapi.repository.entity.Level;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class QuizUpdateDto {

    @NotBlank(message = "Title is mandatory.")
    String title;

    @NotBlank(message = "Description is mandatory.")
    String description;

    @NotBlank(message = "Level is mandatory")
    Level level;

    @NotBlank(message = "Categories is mandatory")
    List<String> categories;

}
