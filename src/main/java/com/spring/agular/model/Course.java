package com.spring.agular.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.agular.enums.Category;
import com.spring.agular.enums.ValueOfEnum;
import com.spring.agular.enums.convertes.CategoryConverter;
import com.spring.agular.enums.convertes.Status;
import com.spring.agular.enums.convertes.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 200, nullable = false)
    private String name;

    @NotNull
    @Column(length = 20, nullable = false)
    @Convert(converter = CategoryConverter.class)
    //@ValueOfEnum(enumClass = Category.class)
    private Category category;


    @NotNull
    @Column(length = 20, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "course")
    @NotNull
    @NotEmpty
    @Valid
    private List<Lesson> lessons = new ArrayList<>();


}
