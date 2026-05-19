package com.chandru.demo.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportsConfig {

	@Bean
	public ICoach swimCoach() {
		return new SwimCoach();
	}
}
