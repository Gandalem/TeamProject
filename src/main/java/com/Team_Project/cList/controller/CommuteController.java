package com.Team_Project.cList.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
	@GetMapping(value = "/commute/start")
	public void saveWorkStart(@RequestParam("workStart") String workStart) {
	    commuteService.saveWorkStart(workStart);
	}
	// DB에 퇴근시간저장
	@GetMapping(value = "/commute/end")
	public void saveWorkEnd() {
		commuteService.saveWorkEnd();
	}
	
	// DB의 출근시간 보내기
	@GetMapping(value = "/commute/vstart", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Commute getLatestCommute() {
	    return commuteRepository.findFirstByOrderByIdxDesc();
	}
	
}
