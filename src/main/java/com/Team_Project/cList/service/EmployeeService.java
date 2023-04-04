package com.Team_Project.cList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Team_Project.cList.repository.EmployeeRepository;
import com.Team_Project.entity.Employee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	public List<Employee> getEmployeesByDepartmentId(Long departmentId){
		return employeeRepository.findEmployeesByDepartmentId(departmentId);
	}
	
	

}
