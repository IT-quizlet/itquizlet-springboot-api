package com.example.itquizletspringbootapi.repository;

import com.example.itquizletspringbootapi.repository.entity.QuestionAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<QuestionAnswerEntity, UUID> {
    List<QuestionAnswerEntity> findByQuestionId(UUID id);
}
