package com.example.SmartApparel;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartApparelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartApparelApplication.class, args);
	}

	// Configures and provides an instance of ModelMapper as a Spring bean
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
