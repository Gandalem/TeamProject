package com.Team_Project.employee;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RequestMapping("/employee")
@Controller
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	@GetMapping("")
	public String Employee() {
		return "employee/em0010";
	}

	//유저 리스트 출력
	@GetMapping("/userList")
	@ResponseBody
	public List<Employee> UserList(@RequestParam("company") Long companyId, @RequestParam("department") Long departmentId) {
		return employeeService.UserList(companyId, departmentId);
	}
	
	//조회 리스트
	@GetMapping("/emList")
	@ResponseBody
	public List<Employee> emList(@RequestParam("company") Long companyId, @RequestParam("department") Long departmentId, @RequestParam("idx") Long idx) {
		return employeeService.emList(companyId, departmentId, idx);
	}
	
	//전체 리스트
	@GetMapping("/List")
	@ResponseBody
	public List<Employee> List() {
		return employeeService.List();
	}
	
	
	@PostMapping("/emCreate")
    public @ResponseBody Employee employeeCreate(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
    }
	
}
