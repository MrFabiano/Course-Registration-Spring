package com.spring.agular.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.agular.model.Lesson;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(
        @JsonProperty("_id")
        Long id,

        @NotNull @NotBlank @Length(min = 5, max = 100)
        String name,

        @NotNull
        @NotBlank
        @Length(max = 100)
        String category,
        List<LessonDTO> lessons){
}
