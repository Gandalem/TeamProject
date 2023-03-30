package com.Team_Project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	
	@GetMapping("/signup")
	public String view() {
		return "sign";
	}
}
