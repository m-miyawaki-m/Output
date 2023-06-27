package com.example.output;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutputController2 {
	
	/*
	 * 参考URL（リクエストパラメータ）
	 * http://localhost:8080/output2?name=James&age=29
	 */
	
	@GetMapping("/output2")
	public String sayOutput(@RequestParam("name") String name,@RequestParam("age") int age) {
		return "output!" + name + "さん" + age;
	}
}
