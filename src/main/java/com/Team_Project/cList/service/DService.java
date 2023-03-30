package com.Team_Project.cList.service;

import org.springframework.stereotype.Service;

import com.Team_Project.cList.repository.DRepository;
import com.Team_Project.entity.Department;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DService {

	private final DRepository dRepository;
	
	public void saveDepartment(Department department) {
		dRepository.save(department);
	}
	
}
