package com.chandru.demo.model;

import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements ICoach {
	@Override
	public String getDailyWorkOut() {
		return "practice dribbling and striking";
	}

	public FootballCoach() {
		System.out.println("inside the footballCoach construtor");
	}

}
