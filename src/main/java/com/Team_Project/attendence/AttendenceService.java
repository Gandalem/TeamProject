package com.Team_Project.attendence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.Team_Project.cList.repository.CommuteRepository;
import com.Team_Project.entity.Commute;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendenceService {
	
	private final AttendenceRepository attendenceRepository;
    
    // commute의 모든 내용 가져오기
    public List<Commute> allList() {
        return attendenceRepository.findAllBy();
    }
    
    
    
    
    public List<Commute> searchAttendance(Long companyId, Long departmentId, Long employeeIdx, int year, int month) {
        return attendenceRepository.findByCompanyIdAndDepartmentIdAndEmployeeIdxAndYearAndMonth(companyId, departmentId, employeeIdx, year, month);
    }


}
