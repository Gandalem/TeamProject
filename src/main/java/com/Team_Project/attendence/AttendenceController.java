package com.Team_Project.attendence;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Team_Project.cList.dto.AttendenceDTO;
import com.Team_Project.cList.service.CListService;
import com.Team_Project.cList.service.CommuteService;
import com.Team_Project.cList.service.DService;
import com.Team_Project.cList.service.EmployeeService;
import com.Team_Project.entity.Commute;
import com.Team_Project.entity.Company;
import com.Team_Project.entity.Department;
import com.Team_Project.entity.Employee;
import com.Team_Project.entity.Member;
import com.Team_Project.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AttendenceController {
	
	private final CListService cListService;
	private final DService dService;
	private final EmployeeService employeeService;
	private final AttendenceService attendenceService;
	
	@GetMapping("/at")
	public String att() {
		return "/attendence/attendence";
	}
	
	// 페이지 출력시 회사선택 select에 리스트 넣기
	@GetMapping("/atten/com")
	@ResponseBody
    public List<Company> getCompany() {
        return cListService.companyList();
    }
	// 회사선택했을때 회사에 맞는 부서리스트 가져오기
	@GetMapping("/atten/de")
	@ResponseBody
	public List<Department> getDepartment(@RequestParam Long companyId){
		List<Department> departments = dService.getDepartments(companyId);
		return departments;
	}
	// 부서를 선택시 부서에 맞는 사원 리스트 가져오기
	@GetMapping("/atten/na")
	@ResponseBody
	public List<Employee> getName(@RequestParam Long departmentId){
	    List<Employee> employee = employeeService.getEmployeesByDepartmentId(departmentId);
	    return employee;
	}
	// 년도와 월에 해당하는 리스트 가져오기
    @GetMapping("/atten/years")
    @ResponseBody
    public List<Integer> getYears(@RequestParam("employeeIdx") Long employeeIdx) {
        return attendenceService.getYearsByEmployeeIdx(employeeIdx);
    }
    @GetMapping("/atten/months")
    @ResponseBody
    public List<Integer> getMonths(@RequestParam("employeeIdx") Long employeeIdx, @RequestParam("year") Integer year) {
        return attendenceService.getMonthsByEmployeeIdxAndYear(employeeIdx, year);
    }
    
    
    // 조회버튼
//    @GetMapping(value = "/atten/search")
//    @ResponseBody
//    public List<Commute> getCommuteList(
//    	     @RequestParam("companyId") Long companyId,
//             @RequestParam("departmentId") Long departmentId,
//             @RequestParam("employeeIdx") Long employeeIdx,
//             @RequestParam("year") Integer year,
//             @RequestParam("month") Integer month
//    		){
//    	return 
//    }
	
	
	
	

	
	
	
    
	
    
}
