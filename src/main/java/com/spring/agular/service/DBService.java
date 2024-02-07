//package com.spring.agular.service;
//
//
//import com.spring.agular.model.Course;
//import com.spring.agular.model.Lesson;
//import com.spring.agular.repository.CourseRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//
//@Service
//public class DBService {
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    public void instanciaObjetos(){
//        Course course = new Course();
//        course.setName("Java-Spring");
//        course.setCategory("Back-end");
//
//        Lesson l = new Lesson();
//        l.setName("Introdução");
//        l.setYouTubeUrl("youtube.com");
//        l.setCourse(course);
//        course.getLessons().add(l);
//
//        Lesson l1 = new Lesson();
//        l1.setName("Angular");
//        l1.setYouTubeUrl("youtubecombb");
//        l1.setCourse(course);
//        course.getLessons().add(l1);
//
//        courseRepository.save(course);
//
//    }
//}
