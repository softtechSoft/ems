package com.softtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EmsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EmsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.out.println("******** RUN configure ********");
		return application.sources(EmsApplication.class);
	}
}
