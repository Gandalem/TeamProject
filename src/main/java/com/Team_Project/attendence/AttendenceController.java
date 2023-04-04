package com.Team_Project.attendence;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AttendenceController {
	
	@GetMapping("/at")
	public String att() {
		return "/attendence/attendence";
	}
}
