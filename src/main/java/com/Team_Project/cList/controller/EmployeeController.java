package com.Team_Project.cList.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Team_Project.cList.service.EmployeeService;
import com.Team_Project.entity.Employee;

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
	
	//리스트 출력
	@GetMapping("/emList")
	public String List() {
		return null;
	}
	
	@PostMapping("/emCreate")
	public ResponseEntity<Employee> employeeCrate(@RequestBody Employee employee) {
		Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(createdEmployee);
	}
	
}
