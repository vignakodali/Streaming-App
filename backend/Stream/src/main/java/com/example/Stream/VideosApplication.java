package com.example.Stream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@EnableJpaRepositories("com.example.Stream.repository") 
public class VideosApplication {
	public static void main(String[] args) {
		SpringApplication.run(VideosApplication.class, args);
	}

}

