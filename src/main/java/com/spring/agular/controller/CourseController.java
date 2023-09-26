package com.spring.agular.controller;

import com.spring.agular.Dtos.CourseDTO;
import com.spring.agular.repository.CourseRepository;
import com.spring.agular.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Validated
@AllArgsConstructor
public class CourseController {

    @Autowired
    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> list(){
        return courseService.list();
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable @NotNull @Positive  Long id){
       return courseService.getById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid CourseDTO course){
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public CourseDTO updateCourse(@PathVariable @NotNull Long id, @RequestBody @Valid CourseDTO course){
        return courseService.updateCourse(id, course);


    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
       courseService.delete(id);
    }
}
