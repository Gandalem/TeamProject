package com.Team_Project.worklog.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Team_Project.cList.service.EmployeeService;
import com.Team_Project.entity.Employee;
import com.Team_Project.entity.Member;
import com.Team_Project.entity.WorkLog;
import com.Team_Project.member.service.MemberService;
import com.Team_Project.worklog.dto.WorkLogDTO;
import com.Team_Project.worklog.repository.WorkLogRepository;
import com.Team_Project.worklog.serivce.WorkLogService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/worklog")
public class WorkLogController {
	
	private final WorkLogService workLogService;
	private final MemberService memberSerivce;
	private final WorkLogRepository workLogRepository;
	private final EmployeeService employeeService;

	
	@GetMapping("")
	public String WorkLog() {
		return "worklog/worklog_list";
	}
	
	
	
	

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
	    this.workLogService.create(workLogDTO.getTitle(), workLogDTO.getContent(), member); 
	    return "redirect:/worklog";
	}
	
//	@GetMapping("/worklog_list")
//	public String WorkLogList(Model model ) {
//		List<WorkLog> workLogList = this.workLogService.getList();
//		model.addAttribute("workLogList", workLogList);
//		return "worklog/worklog_list";
//	}
	
	@GetMapping("/worklog_detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		WorkLog workLog = this.workLogService.getWorkLog(id);
		model.addAttribute("workLog", workLog);
		return "/worklog/worklog_detail";
	}
	

	
	@PutMapping("/{id}/update")
	public ModelAndView updateWorkLog(@PathVariable("id") Long id,
	                       
			@RequestParam("title") String title,
	                                   @RequestParam("content") String content) {
		
		System.out.println("id 출력 : ==> " + id);
		System.out.println("title 출력 : ==> " + title);
		System.out.println("content 출력 : ==> " + content);
		
		
	    WorkLog workLog = workLogRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid id."));
	    workLog.setTitle(title);
	    workLog.setContent(content);
	    
	    workLogRepository.save(workLog);
	    
	    ModelAndView mav = new ModelAndView("/worklog/worklog_detail");
	    mav.addObject("workLog", workLog);
	    mav.addObject("message", "근무일지 업데이트 되었습니다.");
	    
	    
	    
	    return mav;
	}
	
	//유저 리스트 출력
		@GetMapping("/userList")
		@ResponseBody
		public List<WorkLog> UserList(@RequestParam("company") Long companyId, @RequestParam("department") Long departmentId) {
			return workLogService.UserList(companyId, departmentId);
		}
		
		//조회 리스트
		@GetMapping("/worklogList")
		@ResponseBody
		public List<WorkLog> emList(@RequestParam("company") Long companyId, @RequestParam("department") Long departmentId, @RequestParam("id") Long id) {
			return workLogService.emList(companyId, departmentId, id);
		}
		
		//전체 리스트
		@GetMapping("/List")
		@ResponseBody
		public List<WorkLog> List() {
			return workLogService.getList();
		}
		
//		//근무일지 삭제
//		@DeleteMapping("/worklogDelete")
//		@ResponseBody
//		public void employeeDelete(@RequestBody List<String> emailList) {
//			this.workLogService.deleteEmployee(emailList);	
//		}

}
	
	
	

