package com.ciphergx.auroa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling 
@SpringBootApplication
public class AuroaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuroaApplication.class, args);
	}

}
