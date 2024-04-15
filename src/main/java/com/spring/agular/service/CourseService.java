package com.spring.agular.service;

import com.spring.agular.Dtos.CourseDTO;
import com.spring.agular.Dtos.CoursePageDTO;
import com.spring.agular.Dtos.LessonDTO;
import com.spring.agular.Dtos.mapper.CourseMapper;
import com.spring.agular.exception.RecordNotFoundException;
import com.spring.agular.model.Course;
import com.spring.agular.model.Lesson;
import com.spring.agular.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;


//    public List<CourseDTO> list() {
//        return courseRepository.findAll()
//                .stream()
//                .map(courseMapper::toDTO)
//                .collect(Collectors.toList());
//    }
     public CoursePageDTO list(@PositiveOrZero int pageNumber, @Positive @Max(100) int pageSize){
         Page<Course> page =  courseRepository.findAll(PageRequest.of(pageNumber, pageSize));
         List<CourseDTO> courseDTOS = page.get().map(courseMapper::toDTO).collect(Collectors.toList());
         return new CoursePageDTO(courseDTOS, page.getTotalElements(), page.getTotalPages());
     }

    public CourseDTO getById(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toCourse(course)));
    }

    public CourseDTO updateCourse(@Positive Long id, @Valid CourseDTO courseDTO) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(courseMapper.convertCategory(courseDTO.category()));
                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow(()-> new RecordNotFoundException(id));
         }

    public void delete(@NotNull @Positive Long id){
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));

    }
}
