package com.example.itquizletspringbootapi.service.mapper;

import com.example.itquizletspringbootapi.dto.Collection.CollectionDTO;
import com.example.itquizletspringbootapi.repository.entity.CollectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "topics", source = "topics")
    CollectionDTO toDTO(CollectionEntity collectionEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "topics", source = "topics")
    CollectionEntity toEntity(CollectionDTO collectionDTO);

    List<CollectionDTO> toDTOList(List<CollectionEntity> collectionEntities);
    List<CollectionEntity> toEntityList(List<CollectionDTO> collectionDTOs);
}