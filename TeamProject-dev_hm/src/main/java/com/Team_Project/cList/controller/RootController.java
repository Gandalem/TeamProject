package com.Team_Project.cList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
	
	@GetMapping(value = "/")
	public String root() {
		return "main/main";
	}

}
