package com.spring.agular.Dtos;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
public class LessonDTO {

       @Field("_id")
       private String id;

//        @NotNull
//        @NotBlank
//        @Length(min = 5, max = 100)
        private String name;

//        @NotNull
//        @NotBlank
//        @Length(min = 10, max = 100)
        private String youTubeUrl;

}
