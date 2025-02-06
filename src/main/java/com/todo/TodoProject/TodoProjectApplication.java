package com.todo.TodoProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "service", "config", "exception"})
@EntityScan(basePackages = "entity")
@EnableJpaRepositories(basePackages = "repository")
public class TodoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoProjectApplication.class, args);
	}

}
