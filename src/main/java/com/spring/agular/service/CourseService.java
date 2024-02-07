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
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

@Validated
@Service
public class CourseService {

    @Autowired
    private  CourseRepository courseRepository;

//    @Autowired
//    private CourseMapper courseMapper;

    @Autowired
    private ModelMapper modelMapper;


//    public List<CourseDTO> list() {
//        return courseRepository.findAll()
//                .stream()
//                .map(courseMapper::toDTO)
//                .collect(Collectors.toList());
//    }
     public CoursePageDTO list(@PositiveOrZero int pageNumber, @Positive @Max(100) int pageSize){
         Page<Course> page =  courseRepository.findAll(PageRequest.of(pageNumber, pageSize));
         List<CourseDTO> courseDTOS = page.get().map(this::toDTO).collect(toList());
         return new CoursePageDTO(courseDTOS, page.getTotalElements(), page.getTotalPages());
     }

    public Optional<CourseDTO> getById(String id) {
       Optional<Course> course = courseRepository.findById(id);
       if(course.isPresent()){
           return Optional.of(modelMapper.map(course.get(), CourseDTO.class));
       }
        return  Optional.empty();
//        return courseRepository.findById(id).map(courseMapper::toDTO)
//                .orElseThrow(() -> new RecordNotFoundException(id));
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



//        Course course = modelMapper.map(courseDTO, Course.class);
//        courseRepository.save(course);
//
//        CourseDTO response = modelMapper.map(course, CourseDTO.class);
//
//        return Optional.of(response);
       // return Optional.ofNullable(courseMapper.toDTO(courseRepository.save(courseMapper.toCourse(course))));
    }

    public Optional<CourseDTO> updateCourse(String _id, @Valid CourseDTO courseDTO) {
        Optional<Course> course = courseRepository.findById(_id);
        if (course.isPresent()) {
            course.get().setName(courseDTO.getName());
            course.get().setCategory(courseDTO.getCategory());
            course.get().setLessons(List.of(new Lesson()));
            courseRepository.save(course.get());
            return Optional.of(modelMapper.map(course.get(), CourseDTO.class));
        }

        return Optional.empty();
//        return Optional.ofNullable(courseRepository.findById(id)
//                .map(recordFound -> {
//                    Course course = this.toCourse(courseDTO);
//                    recordFound.setName(courseDTO.getName());
//                    recordFound.setCategory(courseDTO.getCategory());
//                    //recordFound.setLessonList(course.getLessonList());
//                    recordFound.getLessons().clear();
//                    course.getLessons().forEach(recordFound.getLessons()::add);
//                    return this.toDTO(courseRepository.save(recordFound));
//                }).orElseThrow(() -> new RecordNotFoundException(id)));
//    }

//        return courseRepository.findById(id)
//                .map(recordFound -> {
//                    Course course = courseMapper.toCourse(courseDTO);
//                    recordFound.setName(courseDTO.getName());
//                    recordFound.setCategory(String.valueOf(courseMapper.convertCategory(courseDTO.getCategory())));
//                    //recordFound.setLessonList(course.getLessonList());
//                    recordFound.getLessons().clear();
//                    course.getLessons().forEach(recordFound.getLessons()::add);
//                    return courseMapper.toDTO(courseRepository.save(recordFound));
//                }).orElseThrow(()-> new RecordNotFoundException(id));
//
//         }
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
