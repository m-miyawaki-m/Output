package com.example.output;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class OutputApplication {
	private static final Logger logger = LoggerFactory.getLogger(OutputApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OutputApplication.class, args);
        logger.info("アプリ起動メッセージ");
	}

}
