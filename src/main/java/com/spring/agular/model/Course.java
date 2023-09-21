package com.spring.agular.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @JsonProperty("_id")
     private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 200, nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Length(max = 100)
    //@Pattern(regexp = "Back-end|Front-end")
    @Column(length = 20, nullable = false)
    private String category;

    @NotNull
    @NotBlank
    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length = 20, nullable = false)
    private String status = "Ativo";

    public Course(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public  Course(){}
}
