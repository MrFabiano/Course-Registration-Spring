//package com.spring.agular.Dtos.mapper;
//
//import com.spring.agular.Dtos.CourseDTO;
//import com.spring.agular.Dtos.LessonDTO;
//import com.spring.agular.enums.Category;
//import com.spring.agular.model.Course;
//import com.spring.agular.model.Lesson;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static java.util.stream.Collectors.toList;
//
//@Component
//public class CourseMapper {
//
//    public  LessonDTO lessonDTO(Lesson lesson) {
//        return new LessonDTO();
//    }
//
//    public CourseDTO toDTO(Course course) {
//        if (course == null) {
//            return null;
//        }
//        List<LessonDTO> lessonDTOS = new ArrayList<>();
//        for (Lesson lesson : course.getLessons()) {
//            LessonDTO dto = lessonDTO(lesson);
//            lessonDTOS.add(dto);
//        }
//        return new CourseDTO();
//    }
//
//    public Course toCourse(CourseDTO courseDTO) {
//        Course course = new Course();
//        if (courseDTO.getName() != null) {
//            course.setName(courseDTO.getName());
//        }
//        course.setName(courseDTO.getName());
//        course.setCategory(String.valueOf(convertCategory(courseDTO.getCategory())));
//
//       List<Lesson> lessons = courseDTO.getLessons().stream().map(
//                lessonDTO -> {
//                    var lesson = new Lesson();
//                    //lesson.setId(lessonDTO.getId());
//                    lesson.setName(lessonDTO.getName());
//                    lesson.setYouTubeUrl(lessonDTO.getYouTubeUrl());
//                    lesson.setCourse(course);
//                    return lesson;
//                }).collect(toList());
//              course.setLessons(lessons);
//        return course;
//    }
//
//    public Category convertCategory(String value) {
//        if (value == null) {
//            return null;
//        }
//        return switch (value) {
//            case "Front-end" -> Category.FRONT_END;
//            case "Back-end" -> Category.BACK_END;
//            default -> throw new IllegalArgumentException("Categoria invalida: " + value);
//        };
//    }
//}
