package com.Team_Project.workLog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Team_Project.entity.WorkLog;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
}
