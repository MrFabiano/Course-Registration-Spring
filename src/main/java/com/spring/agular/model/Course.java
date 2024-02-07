package com.spring.agular.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.agular.enums.Category;
import com.spring.agular.enums.ValueOfEnum;
import com.spring.agular.enums.convertes.CategoryConverter;
import com.spring.agular.enums.convertes.Status;
import com.spring.agular.enums.convertes.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@Document(collection = "course_db")
public class Course {

    @Id
    @JsonProperty("_id")
    private String id;

    private String name;

    @Convert(converter = CategoryConverter.class)
    @ValueOfEnum(enumClass = Category.class)
    private String category;



    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "course_db")
    private List<Lesson> lessons = new ArrayList<>();


}
