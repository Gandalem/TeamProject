package com.Team_Project.worklog.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Team_Project.entity.Member;
import com.Team_Project.entity.WorkLog;
import com.Team_Project.member.service.MemberService;
import com.Team_Project.worklog.dto.WorkLogDTO;
import com.Team_Project.worklog.serivce.WorkLogSerivce;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/worklog")
public class WorkLogController {
	
	private final WorkLogSerivce workLogSerivce;
	private final MemberService memberSerivce; 
	
	@GetMapping("/worklog_create")
	public String createWorkLog() {
		return "worklog/worklog_create";
	}
	
	@PostMapping("/worklog_create")
	public String createWorkLogInfo(@Valid WorkLogDTO workLogDTO, BindingResult bindingResult, Principal principal) {
	    if (bindingResult.hasErrors()) {   //subject,content 가 비어있을때 
	        return "worklog/worklog_create"; 
	    }
	    
	    //System.out.println( "로그인 아이디" + principal.getName());
	    Member member = this.memberSerivce.getMemberByEmail(principal.getName());
	    this.workLogSerivce.create(workLogDTO.getTitle(), workLogDTO.getContent(), member); 
	    return "redirect:/worklog/worklog_list";
	}
	
	@GetMapping("/worklog_list")
	public String WorkLogList(Model model ) {
		List<WorkLog> workLogList = this.workLogSerivce.getList();
		model.addAttribute("workLogList", workLogList);
		return "worklog/worklog_list";
	}
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		WorkLog workLog = this.workLogSerivce.getWorkLog(id);
		model.addAttribute("workLog", workLog);
		return "/worklog/worklog_detail";
	}
	
}
