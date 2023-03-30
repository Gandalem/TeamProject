package com.Team_Project.cList.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Team_Project.cList.service.CListService;
import com.Team_Project.cList.service.DService;
import com.Team_Project.entity.Company;
import com.Team_Project.entity.Department;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clist")
public class CListRestController {
	
	private final CListService cListService;
	private final DService dService;
	
	@GetMapping("/posts")
    public List<Company> getCompany() {
        return cListService.companyList();
    }
	
	@PostMapping("/{companyId}/departments")
    public ResponseEntity<Department> createDepartment(@PathVariable Long companyId, @RequestBody Department department) {
        Optional<Company> optionalCompany = cListService.findcid(companyId);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.addDepartment(department);
            dService.saveDepartment(department);
            return ResponseEntity.ok(department);
        }
        return ResponseEntity.notFound().build();
    }
	
}
