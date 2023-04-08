package com.Team_Project.worklog.serivce;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Team_Project.cList.repository.EmployeeRepository;
import com.Team_Project.config.DataNotFoundException;
import com.Team_Project.entity.Member;
import com.Team_Project.entity.WorkLog;
import com.Team_Project.worklog.repository.WorkLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkLogService {
	
	private final WorkLogRepository workLogRepository;
	
	public List<WorkLog> getList(){
		return workLogRepository.findAll();
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
 
	// select box 이름 호출
	public List<WorkLog> UserList(Long companyId, Long departmentId) {
		return workLogRepository.findByCompany_IdAndDepartment_Id(companyId, departmentId);
	}

	// 조회 리스트
	public List<WorkLog> emList(Long companyId, Long departmentId, Long id) {
		return workLogRepository.findByCompany_IdAndDepartment_IdAndId(companyId, departmentId, id);
	}


	// 근무일지 삭제
//	@Transactional
//	    public void deleteWorkLog(List<String> emailList) {
//	        for (String email : emailList) {
//	        	workLogRepository.deleteByEmail(email);
//	        }
//	}
	
}
