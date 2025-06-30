package com.focus.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.focus.api", "com.focus.common", "com.focus.application"})
public class FocusApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FocusApiApplication.class, args);
	}

}
