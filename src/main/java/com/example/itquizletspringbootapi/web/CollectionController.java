package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.Collection.CollectionDTO;
import com.example.itquizletspringbootapi.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collections")
public class CollectionController {
    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping
    public List<CollectionDTO> getAllCollections() {
        return collectionService.getAllCollections();
    }


    @PostMapping
    public void createCollection(@RequestBody CollectionDTO collectionDTO) {
        collectionService.saveCollection(collectionDTO);
    }

}
