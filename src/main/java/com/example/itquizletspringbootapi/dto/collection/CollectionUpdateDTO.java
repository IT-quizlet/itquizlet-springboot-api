package com.example.itquizletspringbootapi.dto.collection;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


@Value
@Builder
@Jacksonized

public class CollectionUpdateDTO {
    String name;
    String description;
}
