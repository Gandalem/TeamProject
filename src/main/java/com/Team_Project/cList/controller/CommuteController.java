package com.Team_Project.cList.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Team_Project.cList.repository.CommuteRepository;
import com.Team_Project.cList.service.CommuteService;
import com.Team_Project.entity.Commute;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommuteController {
	
	private final CommuteService commuteService;
	private final CommuteRepository commuteRepository;
	
	// DB에 출근시간저장
	@PostMapping(value = "/commute/start")
	public void saveWorkStart(@RequestParam(name = "workStart") String workStart) {
		try {
	    commuteService.saveWorkStart(workStart);
		}catch(Exception e) {
			
		}
	}
	// DB에 퇴근시간저장
	@PostMapping(value = "/commute/end")
	public void saveWorkEnd(@RequestParam(name = "workEnd") String workEnd) {
		try {
	    commuteService.saveWorkEnd(workEnd);
		}catch (Exception e) {
			
		}
	}
	
    // DB의 출근시간 보내기
    @GetMapping(value = "/commute/vstart", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getsLatestCommute() {
        Commute commute = commuteRepository.findFirstByOrderByIdxDesc();
        if (commute == null || commute.getWorkStart() == null) {
            return "-";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H시 m분 s초");
        String formattedWorkStart = commute.getWorkStart().format(formatter);
        return formattedWorkStart;
    }
	
	// DB의 퇴근시간 보내기
	@GetMapping(value = "/commute/vend", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String geteLatestCommute() {
		Commute commute = commuteRepository.findFirstByOrderByIdxDesc();
		if (commute == null || commute.getWorkEnd() == null) {
			return "-";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H시 m분 s초");
		String formattedWorkEnd = commute.getWorkEnd().format(formatter);
		return formattedWorkEnd;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
