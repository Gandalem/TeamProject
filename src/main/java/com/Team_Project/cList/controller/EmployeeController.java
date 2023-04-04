package com.Team_Project.cList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RequestMapping("/employee")
@Controller
public class EmployeeController {
	
	@GetMapping("")
	public String Employee() {
		return "employee/em0010";
	}
	
	//리스트 출력
	@GetMapping("/List")
	public String List() {
		return null;
	}
	
}
