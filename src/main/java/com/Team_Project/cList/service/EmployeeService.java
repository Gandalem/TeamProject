package com.Team_Project.cList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Team_Project.cList.repository.EmployeeRepository;
import com.Team_Project.entity.Employee;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	//select box 이름 호출
	public List<Employee> UserList(Long companyId, Long departmentId) {
		return employeeRepository.findByCompany_IdAndDepartment_Id(companyId, departmentId);
	}
	
	//조회 리스트
	public List<Employee> emList(Long companyId, Long departmentId, Long idx) {
		return employeeRepository.findByCompany_IdAndDepartment_IdAndIdx(companyId, departmentId, idx);
	}
	
	//전체 리스트
	public List<Employee> List() {
		return employeeRepository.findAllBy();
	}
	
	//사원 등록
	@Transactional
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//사원 삭제
	@Transactional
    public void deleteEmployee(List<String> emailList) {
        for (String email : emailList) {
            employeeRepository.deleteByEmail(email);
        }
    }
	
	
	
	
	
	public List<Employee> getEmployeesByDepartmentId(Long departmentId){
		return employeeRepository.findEmployeesByDepartmentId(departmentId);
	}
	
	
}
