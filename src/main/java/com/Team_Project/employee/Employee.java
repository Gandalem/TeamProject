package com.Team_Project.employee;

import com.Team_Project.entity.Company;
import com.Team_Project.entity.Department;
import com.Team_Project.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	private String name;
	
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "company_Id")
	private Company company;
	
	@ManyToOne
	@JoinColumn(name = "department_Id")
	private Department department;
	

}
