package com.Team_Project.attendence;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Team_Project.cList.service.CListService;
import com.Team_Project.cList.service.DService;
import com.Team_Project.cList.service.EmployeeService;
import com.Team_Project.entity.Company;
import com.Team_Project.entity.Department;
import com.Team_Project.entity.Employee;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AttendenceController {
	
	private final CListService cListService;
	private final DService dService;
	private final EmployeeService employeeService;
	
	@GetMapping("/at")
	public String att() {
		return "/attendence/attendence";
	}
	
	// 회사선택 
	@GetMapping("/atten/com")
	@ResponseBody
    public List<Company> getCompany() {
        return cListService.companyList();
    }
	// 부서선택
	@GetMapping("/atten/de")
	@ResponseBody
	public List<Department> getDepartment(@RequestParam Long companyId){
		List<Department> departments = dService.getDepartments(companyId);
		return departments;
	}
	//이름선택
	@GetMapping("/atten/na")
	@ResponseBody
	public List<Employee> getName(@RequestParam Long departmentId){
		List<Employee> employee = employeeService.getEmployeesByDepartmentId(departmentId);
		return employee;
	}
	

	

}
