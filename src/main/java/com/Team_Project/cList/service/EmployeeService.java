package com.Team_Project.cList.service;

import org.springframework.stereotype.Service;

import com.Team_Project.cList.repository.EmployeeRepository;
import com.Team_Project.entity.Employee;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	//등록
	@Transactional
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
}
