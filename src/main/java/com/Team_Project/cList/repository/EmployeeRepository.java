package com.Team_Project.cList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Team_Project.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	
}
