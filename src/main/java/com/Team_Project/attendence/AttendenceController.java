package com.Team_Project.attendence;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Team_Project.cList.service.CListService;
import com.Team_Project.cList.service.CommuteService;
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
	
	private final AttendenceService attendenceService;
	private final CommuteService commuteService;
	
	@GetMapping("/at")
	public String att() {
		return "/attendence/attendence";
	}
	
	// 전체 리스트
    @GetMapping("/atten/list")
    @ResponseBody
    public List<Commute> allList() {
      return attendenceService.allList();
    }
    
    // 비고 DB에 저장하기
    @PostMapping("/atten/saveNote")
    @ResponseBody
    public Commute saveNote(@RequestParam("idx") Long idx, @RequestParam("note") String note) {
        Commute updatedCommute = commuteService.saveNote(idx, note);
        return updatedCommute;
    }
    
    
    @GetMapping("/atten/search")
    @ResponseBody
    public List<Commute> searchAttendance(
            @RequestParam("companyId") Long companyId,
            @RequestParam("departmentId") Long departmentId,
            @RequestParam("employeeIdx") Long employeeIdx,
            @RequestParam("year") int year,
            @RequestParam("month") int month
    ) {
        return attendenceService.searchAttendance(companyId, departmentId, employeeIdx, year, month);
    }
	
	
}
