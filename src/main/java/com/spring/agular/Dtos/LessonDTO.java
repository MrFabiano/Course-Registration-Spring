package com.spring.agular.Dtos;




import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.util.UUID;

@Getter
@Setter
public class LessonDTO {

       @Id
       private String _id  = UUID.randomUUID().toString();
       private String name;
       private String youTubeUrl;

}
