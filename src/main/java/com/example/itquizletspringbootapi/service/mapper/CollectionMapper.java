package com.example.itquizletspringbootapi.service.mapper;

import com.example.itquizletspringbootapi.dto.collection.CollectionDTO;
import com.example.itquizletspringbootapi.repository.entity.CollectionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionMapper {

    CollectionDTO toDTO(CollectionEntity collectionEntity);

    CollectionEntity toEntity(CollectionDTO collectionDTO);

    List<CollectionDTO> toDTOList(List<CollectionEntity> collectionEntities);
    List<CollectionEntity> toEntityList(List<CollectionDTO> collectionDTOs);
}