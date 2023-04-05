package com.Team_Project.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findAllBy();
	
	List<Employee> findByCompany_IdAndDepartment_Id(Long companyId, Long departmentId);
	
	List<Employee> findByCompany_IdAndDepartment_IdAndIdx(Long companyId, Long departmentId, Long idx);
}
