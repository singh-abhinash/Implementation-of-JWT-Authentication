package com.abhinash.implementJWT.springBootJWTAuthentication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by Abhinash Singh - 2024
 */

@SpringBootApplication
public class ImplementJWTAuthentication {


	public static void main(String[] args) {
		SpringApplication.run(ImplementJWTAuthentication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
