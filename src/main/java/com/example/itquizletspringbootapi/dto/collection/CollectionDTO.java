package com.example.itquizletspringbootapi.dto.collection;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Value
@Builder
@Jacksonized
public class CollectionDTO {
    UUID id;
    String name;
    String description;
}