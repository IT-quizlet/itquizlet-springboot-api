package com.example.itquizletspringbootapi.repository;

import com.example.itquizletspringbootapi.repository.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, UUID> {
    List<QuizEntity> findByOwnerId(UUID ownerId);
}
