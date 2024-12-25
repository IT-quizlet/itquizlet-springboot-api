package com.example.itquizletspringbootapi.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "topics")
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String source;

    //@ManyToOne
    //private CategoryEntity category;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    //public CategoryEntity getCategory() {
    //    return category;
    //}

    //public void setCategory(CategoryEntity category) {
    //    this.category = category;
    //}

}
