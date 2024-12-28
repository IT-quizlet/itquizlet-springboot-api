package com.example.itquizletspringbootapi.repository;

import com.example.itquizletspringbootapi.repository.entity.Level;
import com.example.itquizletspringbootapi.repository.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, UUID> {
    List<QuizEntity> findByOwnerId(UUID ownerId);
    @Query("SELECT DISTINCT q FROM QuizEntity q " +
            "LEFT JOIN q.categories cats " +
            "WHERE (:level IS NULL OR q.level = :level) " +
            "AND (:categories IS NULL OR COALESCE(:categories, NULL) IS NULL OR cats IN :categories)")
    List<QuizEntity> findByFilters(@Param("level") Level level, @Param("categories") List<String> categories);
}
