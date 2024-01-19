package com.spring.agular.Dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LessonDTO(

        @JsonProperty("_id")
        Long id,

        @NotNull
        @NotBlank
        @Length(min = 5, max = 100)
        String name,

        @NotNull
        @NotBlank
        @Length(min = 10, max = 100)
        String youTubeUrl) {
}
