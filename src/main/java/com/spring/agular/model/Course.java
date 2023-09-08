package com.spring.agular.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "course")
public class Course {

    @Id
    private String id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String category;

    public Course(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
