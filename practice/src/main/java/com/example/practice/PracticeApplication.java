package com.example.practice;

import com.example.practice.config.JpaConfig;
import com.example.practice.config.MemoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MemoryConfig.class)
//@Import(JpaConfig.class)
@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

}
