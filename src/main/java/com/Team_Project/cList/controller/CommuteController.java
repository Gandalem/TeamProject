package com.Team_Project.cList.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Team_Project.cList.repository.CommuteRepository;
import com.Team_Project.cList.service.CListService;
import com.Team_Project.cList.service.CommuteService;
import com.Team_Project.cList.service.DService;
import com.Team_Project.cList.service.EmployeeService;
import com.Team_Project.entity.Commute;
import com.Team_Project.entity.Company;
import com.Team_Project.entity.Department;
import com.Team_Project.entity.Employee;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommuteController {
	
	private final CommuteService commuteService;
	private final CommuteRepository commuteRepository;
	private final EmployeeService employeeService;
	private final CListService companyService;
	private final DService departmentService;
	
	// DB에 출근시간저장
	@PostMapping(value = "/commute/start")
	public void saveWorkStart(@RequestParam(name = "workStart") String workStart, 
							  @RequestParam(name="today") String today,
							  @RequestParam(name="employeeId") Long employeeId,
	                          @RequestParam(name = "companyId") Long companyId,
	                          @RequestParam(name = "departmentId") Long departmentId) {
	    try {
	        // 날짜 문자열을 LocalDateTime으로 변환
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
	        LocalDateTime todayDate = LocalDate.parse(today, formatter).atStartOfDay();

	        // Commute 객체 생성 및 데이터 설정
	        Commute commute = new Commute();
	        commute.setWorkStart(LocalDateTime.parse(workStart, DateTimeFormatter.ofPattern("yyyy년 M월 d일 H시 m분 s초")));
	        commute.setToday(todayDate);
	        
	        // employee, company, department를 찾아 commute에 설정
	        Employee employee = employeeService.getEmployeeById(employeeId);
	        Company company = companyService.getCompanyById(companyId);
	        Department department = departmentService.getDepartmentById(departmentId);
	        commute.setEmployee(employee);
	        commute.setCompany(company);
	        commute.setDepartment(department);
	        
	        // Commute 객체 저장
	        commuteService.saveCommute(commute);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// DB에 퇴근시간저장
	@PostMapping(value = "/commute/end")
	public void saveWorkEnd(@RequestParam(name = "workEnd") String workEnd) {
		try {
	    commuteService.saveWorkEnd(workEnd);
		}catch (Exception e) {
			
		}
	}
	
    // DB의 출근시간 보내기
    @GetMapping(value = "/commute/vstart", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getsLatestCommute() {
        Commute commute = commuteRepository.findFirstByOrderByIdxDesc();
        if (commute == null || commute.getWorkStart() == null) {
            return "-";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H시 m분 s초");
        String formattedWorkStart = commute.getWorkStart().format(formatter);
        return formattedWorkStart;
    }
	
	// DB의 퇴근시간 보내기
	@GetMapping(value = "/commute/vend", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String geteLatestCommute() {
		Commute commute = commuteRepository.findFirstByOrderByIdxDesc();
		if (commute == null || commute.getWorkEnd() == null) {
			return "-";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H시 m분 s초");
		String formattedWorkEnd = commute.getWorkEnd().format(formatter);
		return formattedWorkEnd;
	}
	
	
	
}
