package com.Team_Project.cList.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Team_Project.cList.error.ApiResponse;
import com.Team_Project.cList.error.ResourceNotFoundException;
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
	
	

	
	
}
