package com.Team_Project.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Company extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Long id;
	
	//회사이름
	private String cname;
	
	//회사주소
	private String address;
	
	//사업자 등록번호
	private String cn;
	
	//회사번
	private String number;
	
	//사업자
	private String name;
	
	@OneToMany
	private List<Department> departmentId;
	
	 public void addDepartment(Department department) {
	        if (this.departmentId == null) {
	            this.departmentId = new ArrayList<>();
	        }
	        this.departmentId.add(department);
	        department.setCompany(this);
	    }

}