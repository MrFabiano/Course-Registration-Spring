package com.spring.agular;

import com.spring.agular.enums.Category;
import com.spring.agular.model.Course;
import com.spring.agular.model.Lesson;
import com.spring.agular.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories(basePackageClasses = 	CourseRepository.class)
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class,
		MongoReactiveAutoConfiguration.class})
public class SpringAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngularApplication.class, args);
	}

	@Id
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			for (int i = 0; i < 20; i++) {
				Course course = new Course();
				course.setName("Java-Spring" + i);
				course.setCategory("Back-end");

				Lesson l = new Lesson();
				l.setName("Introdução");
				l.setYouTubeUrl("youtube.com");
				l.setCourse(course);
				course.getLessons().add(l);

				Lesson l1 = new Lesson();
				l1.setName("Angular");
				l1.setYouTubeUrl("youtubecombb");
				l1.setCourse(course);
				course.getLessons().add(l1);

				courseRepository.save(course);
			}
		};
	}
}


