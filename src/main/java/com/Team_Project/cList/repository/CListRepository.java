package com.Team_Project.cList.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Team_Project.entity.Company;

public interface CListRepository extends JpaRepository<Company, Long> {
	
	List<Company> findAll();
	
	Optional<Company> findCompanyById(Long id);
	
}
