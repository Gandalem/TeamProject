package com.Team_Project.woktlog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Team_Project.entity.WorkLog;
import com.Team_Project.woktlog.repository.WorkLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkLogService {

    private final WorkLogRepository workLogRepository;

    public List<WorkLog> getAllWorkLogs() {
        return workLogRepository.findAll();
    }

    public WorkLog createWorkLog(WorkLog workLog) {
        workLog.setCreatedAt(LocalDateTime.now());
        return workLogRepository.save(workLog);
    }

    public Optional<WorkLog> getWorkLogById(Long id) {
        return workLogRepository.findById(id);
    }

    public WorkLog updateWorkLog(WorkLog workLog) {
        workLog.setUpdatedAt(LocalDateTime.now());
        return workLogRepository.save(workLog);
    }

    public void deleteWorkLog(Long id) {
        workLogRepository.deleteById(id);
    }
}