package com.example.itquizletspringbootapi.repository.entity;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_responses")
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quiz;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private QuestionAnswerEntity selectedAnswer;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime respondedAt;

}
