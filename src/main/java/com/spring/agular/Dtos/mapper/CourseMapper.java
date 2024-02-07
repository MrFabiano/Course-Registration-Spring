package com.spring.agular.Dtos.mapper;

import com.spring.agular.Dtos.CourseDTO;
import com.spring.agular.Dtos.LessonDTO;
import com.spring.agular.enums.Category;
import com.spring.agular.model.Course;
import com.spring.agular.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        List<LessonDTO> lessonDTOS = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(),
                        lesson.getYouTubeUrl()))
                .collect(Collectors.toList());
        return new CourseDTO(course.getId(), course.getName(),
                course.getCategory().getValue(), lessonDTOS);
    }

    public Course toCourse(CourseDTO courseDTO) {
        Course course = new Course();
        if (courseDTO.name() != null) {
            course.setName(courseDTO.name());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategory(courseDTO.category()));

       List<Lesson> lessons = courseDTO.lessons().stream().map(
                lessonDTO -> {
                    var lesson = new Lesson();
                   // lesson.setId(lessonDTO.id());
                    lesson.setName(lessonDTO.name());
                    lesson.setYouTubeUrl(lessonDTO.youTubeUrl());
                    lesson.setCourse(course);
                    return lesson;
                }).collect(Collectors.toList());
              course.setLessons(lessons);
        return course;
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
}
