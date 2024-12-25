package com.example.itquizletspringbootapi.repository;

import com.example.itquizletspringbootapi.repository.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {
    @Query("SELECT DISTINCT c FROM CollectionEntity c LEFT JOIN FETCH c.topics")
    List<CollectionEntity> findAll();
}