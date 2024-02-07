package com.spring.agular.Dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.agular.enums.Category;
import com.spring.agular.enums.ValueOfEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseDTO{

    @Field(value = "_id")
    private String _id;

    private String name;

     @ValueOfEnum(enumClass = Category.class)
     private String category;

     private List<LessonDTO> lessons = new ArrayList<>();

}
