package com.mj147;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:message.properties")
public class DurakCardGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(DurakCardGameApplication.class, args);
	}

}
