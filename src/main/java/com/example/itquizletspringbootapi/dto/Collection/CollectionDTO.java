package com.example.itquizletspringbootapi.dto.Collection;

import com.example.itquizletspringbootapi.dto.Topic.TopicDTO;
import com.example.itquizletspringbootapi.repository.entity.TopicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDTO {
    private Long id;
    private String name;
    private List<TopicDTO> topics;
}