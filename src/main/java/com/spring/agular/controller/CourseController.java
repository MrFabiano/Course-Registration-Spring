package com.spring.agular.controller;

import com.spring.agular.Dtos.CourseDTO;
import com.spring.agular.Dtos.CoursePageDTO;
import com.spring.agular.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
@Validated
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<CoursePageDTO> list(@RequestParam(defaultValue = "0")
                                              @PositiveOrZero int pageNumber,
                                              @RequestParam(defaultValue = "10")
                                              @Positive @Max(100) int pageSize) {
        return ResponseEntity.ok(courseService.list(pageNumber, pageSize));
    }

//    @GetMapping
//    public List<CourseDTO> list(){
//        return courseService.list();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable String id) {
        Optional<CourseDTO> courseDTO = courseService.getById(id);
        if(courseDTO.isPresent()){
            return ResponseEntity.ok(courseDTO.get());
        }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<CourseDTO> create(@RequestBody @Valid CourseDTO course){
         Optional<CourseDTO> courseDTO = courseService.create(course);
         if(courseDTO.isPresent()){
             return new ResponseEntity<>(courseDTO.get(), HttpStatus.CREATED);
         }

         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable String id, @RequestBody @Valid CourseDTO courseDTO){

        Optional<CourseDTO> update = courseService.updateCourse(id, courseDTO);
        if(update.isPresent()){
            return ResponseEntity.ok(update.get());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @CachePut
    public ResponseEntity<Void> delete(@PathVariable String id){
       courseService.delete(id);
       return ResponseEntity.noContent().build();
    }
}
