package com.Team_Project.worklog.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

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

	@GetMapping("")
	public String WorkLogList(Model model ) {
		List<WorkLog> workLogList = this.workLogService.getList();
		model.addAttribute("workLogList", workLogList);
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
	
	
	@GetMapping("/worklog_detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
	    WorkLog workLog = this.workLogService.getWorkLog(id);
	    
	    if (workLog == null) {
	        // 오류 메시지를 설정하거나 다른 페이지로 리다이렉트하는 등의 처리를 수행합니다.
	        return "redirect:/error";
	    }
	    
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
	
	// 유저 리스트 출력
	@GetMapping("/userList")
	@ResponseBody
	public List<Member> UserList(@RequestParam("company") Long companyId,
			@RequestParam("department") Long departmentId) {
		return workLogService.UserList(companyId, departmentId);
	}

	// 조회 리스트
	@GetMapping("/worklogList")
	@ResponseBody
	public Map<String, Object> emList(@RequestParam("company") Long companyId, @RequestParam("department") Long departmentId,
	        @RequestParam("memberId") Long memberId) {
	    List<WorkLog> workLogList = workLogService.emList(companyId, departmentId, memberId);
	    List<WorkLogDTO> workLogDTOList = new ArrayList<>();

	    for (WorkLog worklog : workLogList) {
	        WorkLogDTO dto = new WorkLogDTO();
	        dto.setMemberName(worklog.getMember().getName());
	        dto.setTitle(worklog.getTitle());
	        dto.setContent(worklog.getContent());
	        dto.setCreateDate(worklog.getCreateDate().toString());
	        workLogDTOList.add(dto);
	    }

	    Map<String, Object> resultMap = new HashMap<>();
	    resultMap.put("data", workLogDTOList);
	    return resultMap;
	}




	// 전체 리스트
	@GetMapping("/List")
	@ResponseBody
	public List<WorkLogDTO> List() {
	    List<WorkLog> workLogList = workLogService.getList();
	    List<WorkLogDTO> workLogDTOList = new ArrayList<>();

	    for (WorkLog worklog : workLogList) {
	        WorkLogDTO dto = new WorkLogDTO();
	        dto.setId(worklog.getId());
	        dto.setMemberName(worklog.getMember().getName());
	        dto.setTitle(worklog.getTitle());
	        dto.setContent(worklog.getContent());
	        dto.setCreateDate(worklog.getCreateDate().toString());
	        workLogDTOList.add(dto);
	    }

	    return workLogDTOList;
	}

	
	//근무일지 삭제
	@DeleteMapping("/worklogDelete")
	public ResponseEntity<Map<String, String>> worklogDelete(Principal principal, @RequestBody List<Long> workLogIds) {
	    Map<String, String> response = new HashMap<>();
	    try {
	        for (Long workLogId : workLogIds) {
	            WorkLog workLog = this.workLogService.getWorkLog(workLogId);
	            if (!workLog.getMember().getName().equals(principal.getName())) {
	                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	            }
	            workLogRepository.deleteById(workLogId);
	        }
	        response.put("status", "success");
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        response.put("status", "error");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	

	


//		//근무일지 삭제
//		@DeleteMapping("/worklogDelete")
//		@ResponseBody
//		public void employeeDelete(@RequestBody List<String> emailList) {
//			this.workLogService.deleteWorkLog(emailList);	
//		}

}
	
	
	

