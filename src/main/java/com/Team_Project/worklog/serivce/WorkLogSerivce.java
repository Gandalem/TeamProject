package com.Team_Project.worklog.serivce;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Team_Project.config.DataNotFoundException;
import com.Team_Project.entity.Member;
import com.Team_Project.entity.WorkLog;
import com.Team_Project.worklog.repository.WorkLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkLogSerivce {
	
	private final WorkLogRepository workLogRepository;
	
	public List<WorkLog> getList(){
		return this.workLogRepository.findAll();
	}

	public WorkLog getWorkLog(Long id) {
	    Optional<WorkLog> workLog = this.workLogRepository.findById(id);
	    if(workLog.isPresent()) {
	        return workLog.get();
	    } else {
	        throw new DataNotFoundException("workLog not found");
	    }
	}

	public void create(String title, String content, Member member) {
		WorkLog wl = new WorkLog();
		wl.setTitle(title);
		wl.setContent(content);
		wl.setCreateDate(LocalDateTime.now());
		wl.setMember(member);
		
		this.workLogRepository.save(wl);
		
	}
	public void modify(WorkLog workLog, String title, String content) {
        workLog.setTitle(title);
        workLog.setContent(content);
        workLog.setModifyDate(LocalDateTime.now());
        this.workLogRepository.save(workLog);
    }
 
	public void delete(WorkLog workLog) {
		this.workLogRepository.delete(workLog);
	}
	
}
