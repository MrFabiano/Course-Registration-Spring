package com.spring.agular.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "lesson_db")
public class Lesson {

     @Id
     private String _id;
     private String name;

     private String youTubeUrl;

     private Course course;

}
