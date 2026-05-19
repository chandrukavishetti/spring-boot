package com.chandru.demo.model;

import org.springframework.stereotype.Component;

@Component
public class BadmintonCoach implements ICoach {

	@Override
	public String getDailyWorkOut() {
		return "practice smashing and service";
	}

	public BadmintonCoach() {
		System.out.println("Inside the BadmintonCoach Constructor");
	}

}
