package com.spring.agular.Dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.agular.enums.Category;
import com.spring.agular.enums.ValueOfEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseDTO{


       @Field("_id")
       private String id;


//        @Length(min = 5, max = 100)
//        @NotNull
//        @NotBlank
      private String name;

//        @Length(max = 100)
     @ValueOfEnum(enumClass = Category.class)
     private String category;

//        @NotNull
//        @NotEmpty
//        @Valid
     private List<LessonDTO> lessons;

}
