package com.Team_Project.worklog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Team_Project.entity.WorkLog;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {

}