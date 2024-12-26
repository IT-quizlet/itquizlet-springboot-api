package com.example.itquizletspringbootapi.service;

import com.example.itquizletspringbootapi.dto.collection.CollectionDTO;

import java.util.List;

public interface CollectionService {
    List<CollectionDTO> getAllCollections();
    void saveCollection(CollectionDTO collectionDTO);
}
