package com.example.itquizletspringbootapi.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public class TopicCreateDTO {

    @NotBlank
    String name;

    UUID categoryId;

}
