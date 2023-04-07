package com.Team_Project.cList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Team_Project.cList.repository.CListRepository;
import com.Team_Project.entity.Company;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CListService {
	
	private final CListRepository cListRepository;
	
	public List<Company> companyList(){
		return cListRepository.findAll();
	}
	
	
	public Company saveCompany(Company company) {
		return cListRepository.save(company);
	}
	
}
