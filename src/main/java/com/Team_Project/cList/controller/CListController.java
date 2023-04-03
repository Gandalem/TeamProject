package com.Team_Project.cList.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Team_Project.cList.repository.DRepository;
import com.Team_Project.cList.service.CListService;
import com.Team_Project.cList.service.DService;
import com.Team_Project.entity.Company;
import com.Team_Project.entity.Department;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/clist")
@Controller
public class CListController {
	
	private final CListService cListService;
	private final DService dService;
	
	@GetMapping("/main")
	public String clist() {
		
		List<Company> cList = cListService.companyList();
		
//		model.addAttribute("list", cList);
		
		return "company/cList";
	}
	
	@PostMapping("/save")
    public @ResponseBody Company saveCompany(@RequestBody Company company) {
        return cListService.saveCompany(company);
    }
	
	@GetMapping("/getDepartments")
	@ResponseBody
	public List<Department> getDepartments(@RequestParam Long companyId) {
		List<Department> departments = dService.getDepartments(companyId);
		return departments;
	}
	
	@PostMapping("/dsave")
    public @ResponseBody void saveDepartment(@RequestBody Department department) {
		dService.saveDepartment(department);
        
    }
	
	@GetMapping("/getDepartments_null")
	@ResponseBody
	public List<Department> dfindall(){
		return dService.dfindall();
	}
	
	
	 
	
}