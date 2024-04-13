package com.spring.agular.Dtos;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LessonDTO {

       @Id
       private Long id;
       private String name;
       private String youTubeUrl;

}
