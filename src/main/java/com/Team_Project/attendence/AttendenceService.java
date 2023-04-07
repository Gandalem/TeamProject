package com.Team_Project.attendence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.Team_Project.cList.repository.CommuteRepository;
import com.Team_Project.entity.Commute;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendenceService {
	
	private final CommuteRepository commuteRepository;
	private final AttendenceRepository AttendenceRepository;
	
	// DB의 today의 리스트에서 년도만 빼서 넣기
    public List<Integer> getYearsByEmployeeIdx(Long employeeIdx) {
        List<LocalDateTime> todayList = commuteRepository.findTodayByEmployeeIdx(employeeIdx);
        return todayList.stream()
                .map(today -> today.getYear())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    // DB의 today의 리스트에서 월만 빼서 넣기
    public List<Integer> getMonthsByEmployeeIdxAndYear(Long employeeIdx, Integer year) {
        List<LocalDateTime> todayList = commuteRepository.findTodayByEmployeeIdx(employeeIdx);
        return todayList.stream()
                .filter(today -> today.getYear() == year)
                	//위의 filter는 해당하는 년도에 대한 월을 가져오기 위한 코드
                .map(today -> today.getMonthValue())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

    }
    
    
    public List<Commute> allList() {
        return AttendenceRepository.findAllBy();
    }
    

}
