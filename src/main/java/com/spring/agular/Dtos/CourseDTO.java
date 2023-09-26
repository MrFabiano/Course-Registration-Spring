package com.spring.agular.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(

        @JsonProperty("_id")
        Long id,

        @Length(min = 5, max = 100)
        @NotNull
        @NotBlank
        String name,

        @Length(max = 100)
        //@ValueOfEnum(enumClass = Category.class)
        @NotNull
        @NotBlank
        String category,

        @NotNull
        List<LessonDTO> lessons){

}
