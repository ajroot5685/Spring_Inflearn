package com.example.practicestart;

import com.example.practicestart.config.JpaConfig;
import com.example.practicestart.config.MemoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import(MemoryConfig.class)
@Import(JpaConfig.class)
@SpringBootApplication
public class PracticeStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeStartApplication.class, args);
	}

}
