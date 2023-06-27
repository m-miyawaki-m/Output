package com.example.output;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class OutputController5 {

	/*
	 * 参考URL
	 * http://localhost:8080/output5?name=java
	 */
	@GetMapping("/output5")
	public String sayOutput(@RequestParam("name") String name, ModelAndView mv) {
		mv.setViewName("Output2");
		mv.addObject("name", name);
		return "output!" + name + "さん";
	}
}
