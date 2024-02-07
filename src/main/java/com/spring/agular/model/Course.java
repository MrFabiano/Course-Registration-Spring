package com.spring.agular.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.agular.enums.Category;
import com.spring.agular.enums.ValueOfEnum;
import com.spring.agular.enums.convertes.CategoryConverter;
import com.spring.agular.enums.convertes.Status;
import com.spring.agular.enums.convertes.StatusConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.OneToMany;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;


@Data
@Document(collection = "course_db")
public class Course {

    @Id
    private String id;

    private String name;

    @Convert(converter = CategoryConverter.class)
    @ValueOfEnum(enumClass = Category.class)
    private String category;



    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

//    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "course_db")
    private List<Lesson> lessons = new ArrayList<>();


}
