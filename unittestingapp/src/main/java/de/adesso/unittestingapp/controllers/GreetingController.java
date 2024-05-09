package de.adesso.unittestingapp.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.adesso.unittestingapp.dtos.GreetingDto;

@RestController
@RequestMapping(path="/api")
public class GreetingController {
	
	AtomicLong idRiser = new AtomicLong();

	@GetMapping("/greeting")
	public GreetingDto greeting(@RequestParam(value="name")String name) {
		String message = String.format("Hi %s!", name);
		
		GreetingDto greetingObj = new GreetingDto();
		greetingObj.setMessage(message);
		greetingObj.setId(idRiser.getAndIncrement());
		
		return greetingObj;
	}
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "helloWorld";
	}
}
