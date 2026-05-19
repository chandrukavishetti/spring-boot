package com.chandru.demo.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EngineConfig {

	@Bean
	public Engine hydrozenEngine() {
		return new HydrozenEngine();
	}

}
