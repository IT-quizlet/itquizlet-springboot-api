package com.example.itquizletspringbootapi.repository.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "topics")
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String name;
}
