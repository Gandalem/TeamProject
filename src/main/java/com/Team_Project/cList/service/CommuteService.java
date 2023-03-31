package com.Team_Project.cList.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Team_Project.cList.repository.CommuteRepository;
import com.Team_Project.entity.Commute;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommuteService {
	
	private final CommuteRepository commuteRepository;
	
	// 메인페이지에서 출근버튼 클릭시 DB에 저장
    public void saveWorkStart(String startTime) {
        Commute commute = new Commute();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 H시 m분 s초");
        LocalDateTime localDateTime = LocalDateTime.parse(startTime, formatter);
        commute.setWorkStart(localDateTime);
        commuteRepository.save(commute);
    }

    // 메인페이지에서 퇴근버튼 클릭시 DB에 저장
    public void saveWorkEnd(String endTime) {
        List<Commute> commutes = commuteRepository.findAll();
        // 리스트의 마지막 레코드를 가져오기
        Commute commute = commutes.get(commutes.size() - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 H시 m분 s초");

        LocalDateTime workEndDateTime = LocalDateTime.parse(endTime, formatter);
        commute.setWorkEnd(workEndDateTime);
        commuteRepository.save(commute);
    	    
    }

    
    
    
    
    
    // DB에 저장된 출, 퇴근 시간 리스트 가져오기
    public Commute getLastCommute() {
        return commuteRepository.findFirstByOrderByIdxDesc();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
