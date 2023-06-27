package com.example.output;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutputController4 {
	
	/*
	 * 参考URL
	 * http://localhost:8080/output.html
	 */
	@PostMapping("/output4")
	public String sayOutput(@RequestParam("name") String name) {
		return "output!" + name + "さん";
	}
}
