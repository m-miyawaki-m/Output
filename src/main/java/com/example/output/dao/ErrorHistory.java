package com.example.output.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorHistory {
	private int seq;
	private String error;
	private String result;
}
