package com.example.output;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutputController3 {
	
	/*
	 * 参考URL（URIに変数）
	 * http://localhost:8080/output3/tanaka
	 */
	
	@GetMapping("/output3/{name}")
	public String sayOutput(@PathVariable("name") String name) {
		return "output!" + name + "さん";
	}
}
