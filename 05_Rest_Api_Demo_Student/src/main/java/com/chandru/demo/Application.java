package com.chandru.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

//
//Controller
//↓
//RequestDTO
//↓
//Service
//↓
//Mapper
//↓
//Entity
//↓
//Repository
//↓
//Database
//
//
//RESPONSE
//
//Database
//↓
//Entity
//↓
//Mapper
//↓
//ResponseDTO
//↓
//Controller