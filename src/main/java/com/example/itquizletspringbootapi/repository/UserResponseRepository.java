package com.example.itquizletspringbootapi.repository;

import com.example.itquizletspringbootapi.repository.entity.QuestionAnswerEntity;
import com.example.itquizletspringbootapi.repository.entity.UserResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponseEntity, UUID> {
    List<UserResponseEntity> findUserResponsesByUser_Username(String username);
    List<UserResponseEntity> findByQuizId(UUID quizId);
    Optional<QuestionAnswerEntity> findAnswerById(UUID answerId);
}
