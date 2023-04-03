package com.Team_Project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class Department extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private Long id;
	
	//부서이름
	private String dname;
	
	//회사idx
	@ManyToOne
	@JoinColumn(name = "company")
	private Company company;
	
	

}
