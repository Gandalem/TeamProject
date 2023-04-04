package com.Team_Project.cList.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.Team_Project.cList.service.CommuteService;
import com.Team_Project.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RootController {
	
	private final MemberRepository memberRepository;
	
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/")
	public String root(Model model, Principal principal) {
//	    String email = principal.getName();
//
//	    // 사용자 정보 가져오기
//	    Optional<Member> memberOptional = memberRepository.findByEmail(email);
//	    
//	    // Optional 언래핑
//	    Member member = memberOptional.orElse(null);
//	    
//	    // 사용자 정보가 존재하면, 모델에 이름, 부서, 회사 추가
//	    if (member != null) {
//	        model.addAttribute("name", member.getName());
//	        model.addAttribute("departmentName", member.getDepartmentName());
//	        model.addAttribute("companyName", member.getCompanyName());
//	    }
	    
	    return "main/main";
	}

}
