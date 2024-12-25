package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.Collection.CollectionDTO;
import com.example.itquizletspringbootapi.repository.CollectionRepository;
import com.example.itquizletspringbootapi.repository.entity.CollectionEntity;
import com.example.itquizletspringbootapi.service.CollectionService;
import com.example.itquizletspringbootapi.service.mapper.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository collectionRepository;
    private final CollectionMapper collectionMapper;

    @Autowired
    public CollectionServiceImpl(CollectionRepository collectionRepository, CollectionMapper collectionMapper) {
        this.collectionRepository = collectionRepository;
        this.collectionMapper = collectionMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectionDTO> getAllCollections() {
        List<CollectionEntity> collections = collectionRepository.findAll();
        return collections.stream()
                .map(collectionMapper::toDTO)  // Use the mapper instead of manual mapping
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveCollection(CollectionDTO collectionDTO) {
        CollectionEntity collectionEntity = collectionMapper.toEntity(collectionDTO);
        collectionRepository.save(collectionEntity);
    }
}