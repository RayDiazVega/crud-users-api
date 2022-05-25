package com.colpatria.crudusersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CrudUsersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudUsersApiApplication.class, args);
	}

}
