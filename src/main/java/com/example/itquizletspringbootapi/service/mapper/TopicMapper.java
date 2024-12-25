package com.example.itquizletspringbootapi.service.mapper;

import com.example.itquizletspringbootapi.dto.Topic.TopicDTO;
import com.example.itquizletspringbootapi.repository.entity.TopicEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicDTO toDTO(TopicEntity topicEntity);

    TopicEntity toEntity(TopicDTO topicDTO);

    List<TopicDTO> toDTOList(List<TopicEntity> topicEntities);
    List<TopicEntity> toEntityList(List<TopicDTO> topicDTOs);
}
