package com.example.itquizletspringbootapi.dto.topic;

import java.util.UUID;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized

public class TopicDTO {
    String name;
    UUID categoryId;
}
