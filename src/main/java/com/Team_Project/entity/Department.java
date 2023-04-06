package com.Team_Project.entity;

import java.util.List;

import com.Team_Project.employee.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
@Entity
public class Department extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//부서이름
	private String dname;
	
	//회사idx
	@ManyToOne
	@JoinColumn(name = "company_Id")
	private Company company;
	
	@OneToMany
	private List<Employee> employeeId;
	
	@OneToMany
	@JoinColumn(name = "member_Id")
	private List<Member> memberId; 
	

}
