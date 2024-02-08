package com.spring.agular.service;

import com.spring.agular.Dtos.CourseDTO;
import com.spring.agular.Dtos.CoursePageDTO;

import com.spring.agular.Dtos.LessonDTO;
import com.spring.agular.enums.Category;

import com.spring.agular.exception.RecordNotFoundException;
import com.spring.agular.model.Course;
import com.spring.agular.model.Lesson;
import com.spring.agular.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

@Validated
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;


    //    public List<CourseDTO> list() {
//        return courseRepository.findAll()
//                .stream()
//                .map(courseMapper::toDTO)
//                .collect(Collectors.toList());
//    }
    public CoursePageDTO list(@PositiveOrZero int pageNumber, @Positive @Max(100) int pageSize) {
        Page<Course> page = courseRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<CourseDTO> courseDTOS = page.get().map(this::toDTO).collect(toList());
        return new CoursePageDTO(courseDTOS, page.getTotalElements(), page.getTotalPages());
    }

    public Optional<CourseDTO> getById(String id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return Optional.of(modelMapper.map(course.get(), CourseDTO.class));
        }
        return Optional.empty();
    }

    public Optional<CourseDTO> create(CourseDTO courseDTO) {

        Course course = modelMapper.map(courseDTO, Course.class);
        courseRepository.save(course);

        course.setName(courseDTO.getName());
        course.setCategory(courseDTO.getCategory());

        CourseDTO response = modelMapper.map(course, CourseDTO.class);


        List<Lesson> lessons = courseDTO.getLessons().stream().map(
                lessonDTO -> {
                    var lesson = new Lesson();
                    //lesson.set_id(lessonDTO.get_id());
                    lesson.setName(lessonDTO.getName());
                    lesson.setYouTubeUrl(lessonDTO.getYouTubeUrl());
                    lesson.setCourse(course);
                    return lesson;
                }).collect(toList());
        course.setLessons(lessons);

        return Optional.of(response);

    }

    public Optional<CourseDTO> updateCourse(String id, @Valid CourseDTO courseDTO) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            course.get().setName(courseDTO.getName());
            course.get().setCategory(courseDTO.getCategory());
            course.get().setLessons(List.of(new Lesson()));
            course.get().setLessons(courseDTO.getLessons().stream().map(lessonDTO -> {
                        var lesson = new Lesson();
                        lesson.set_id(lessonDTO.get_id());
                        lesson.setName(lessonDTO.getName());
                        lesson.setYouTubeUrl(lessonDTO.getYouTubeUrl());
                        lesson.setCourse(new Course());
                        return lesson;
                    }).collect(toList()));
            courseRepository.save(course.get());
            return Optional.of(modelMapper.map(course.get(), CourseDTO.class));
        }

        return Optional.empty();
    }


    public void delete(String id){
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));

    }
        public Category convertCategory(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria invalida: " + value);
        };
   }

    public  LessonDTO lessonDTO(Lesson lesson) {
        return new LessonDTO();
    }
    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        for (Lesson lesson : course.getLessons()) {
            LessonDTO dto = lessonDTO(lesson);
            lessonDTOS.add(dto);
        }
        return new CourseDTO();
    }

        public Course toCourse(CourseDTO courseDTO) {
        Course course = new Course();
        if (courseDTO.getName() != null) {
            course.setName(courseDTO.getName());
        }
        course.setName(courseDTO.getName());
        course.setCategory(courseDTO.getCategory());

       List<Lesson> lessons = courseDTO.getLessons().stream().map(
                lessonDTO -> {
                    var lesson = new Lesson();
                    //lesson.setId(lessonDTO.getId());
                    lesson.setName(lessonDTO.getName());
                    lesson.setYouTubeUrl(lessonDTO.getYouTubeUrl());
                    lesson.setCourse(course);
                    return lesson;
                }).collect(toList());
              course.setLessons(lessons);
        return course;
    }
}
