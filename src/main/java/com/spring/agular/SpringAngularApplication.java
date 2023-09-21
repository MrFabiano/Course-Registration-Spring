package com.spring.agular;

import com.spring.agular.model.Course;
import com.spring.agular.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, MongoReactiveAutoConfiguration.class})
//@EnableMongoRepositories
public class SpringAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngularApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();
			Course course = new Course();
			course.setName("Java-Spring");
			course.setCategory("Back-end");
			courseRepository.save(course);

		};
	}
}


