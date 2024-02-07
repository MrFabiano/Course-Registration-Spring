package com.spring.agular.Dtos;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.UUID;

@Getter
@Setter
public class LessonDTO {

       @Id
       private String _id  = UUID.randomUUID().toString();
       private String name;
       private String youTubeUrl;

}
