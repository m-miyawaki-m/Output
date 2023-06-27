package com.example.output;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutputController {
	@GetMapping("/output")
	public String sayOutput() {
		return "output!";
	}
}
