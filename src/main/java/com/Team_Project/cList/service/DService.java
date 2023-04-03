package com.Team_Project.cList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Team_Project.cList.error.ResourceNotFoundException;
import com.Team_Project.cList.repository.CListRepository;
import com.Team_Project.cList.repository.DRepository;
import com.Team_Project.entity.Company;
import com.Team_Project.entity.Department;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DService {
	
	private final CListRepository cListRepository;
	private final DRepository dRepository;
	
	public List<Department> getDepartments(Long companyId) {
	    Company company = cListRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("Invalid company id: " + companyId));
	    return dRepository.findByCompany(company);
	}

	public Department saveDepartment(Department department) {
		return dRepository.save(department);
	}
	
	public List<Department> dfindall() {
		return dRepository.findAll();
	}
	
}
