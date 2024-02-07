package com.spring.agular.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "lesson_db")
public class Lesson {

//    @JsonProperty("_id")
//    private String id
     @Id
     private String id;


    private String name;

    private String youTubeUrl;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "course_db", nullable = false)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Course course;

}
