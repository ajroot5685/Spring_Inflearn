package com.example.pirosquare_spring;

import com.example.pirosquare_spring.domain.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(nameGenerator = FullBeanNameGenerator.class) // 커스텀한 빈이름생성클래스 등록
public class PirosquareSpringApplication {

	public static void main(String[] args) {

		SpringApplication.run(PirosquareSpringApplication.class, args);
	}

}
