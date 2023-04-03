package com.Team_Project.cList.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Team_Project.cList.service.CommuteService;
import com.Team_Project.member.Service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RootController {
	
	private final MemberService memberService;
	private final CommuteService commuteService;
	
	//@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/")
	public String root(Model model, Principal principal) {
		
//		String email = principal.getName();
		
		
		
		return "main/main";
	}

	
	
	
	
	
//	@GetMapping("/")
//	public String home(Model model, Principal principal) {
//	    String email = principal.getName();
//	    Member member = memberService.findByEmail(email)
//	            .orElseThrow(() -> new UsernameNotFoundException("해당 이메일을 가진 사용자가 존재하지 않습니다."));
//	    String name = member.getName();
//	    model.addAttribute("name", name);
////	    List<Commute> commuteList = commuteService.getCommuteByAuthor(member);
////	    model.addAttribute("commuteList", commuteList);
//	    return "main/main";
//	}
}
