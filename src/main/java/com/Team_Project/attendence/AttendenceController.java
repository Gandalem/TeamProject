package com.Team_Project.attendence;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Team_Project.cList.service.CListService;
import com.Team_Project.cList.service.DService;
import com.Team_Project.cList.service.EmployeeService;
import com.Team_Project.entity.Commute;
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
	private final AttendenceService attendenceService;
	
	@GetMapping("/at")
	public String att() {
		return "/attendence/attendence";
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
    
	// 전체 리스트
    @GetMapping("/atten/search")
    @ResponseBody
    public List<Commute> allList() {
      return attendenceService.allList();
    }
	
	
}
