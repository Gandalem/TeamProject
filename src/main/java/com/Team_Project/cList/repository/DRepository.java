package com.Team_Project.cList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Team_Project.entity.Company;
import com.Team_Project.entity.Department;

public interface DRepository extends JpaRepository<Department, Long> {
	
	List<Department> findByCompany(Company company);
	
	// 로그인 후 부서를 select하는 코드
	Department findByDname(String Dname);
}
