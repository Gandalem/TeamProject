package com.Team_Project.worklog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Team_Project.entity.WorkLog;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {

	List<WorkLog> findAllBy();

	List<WorkLog> findByCompany_IdAndDepartment_Id(Long companyId, Long departmentId);
	
	List<WorkLog> findByCompany_IdAndDepartment_IdAndId(Long companyId, Long departmentId, Long id);

	//List<WorkLog> findByCompany_IdAndDepartment_IdAndIdx(Long companyId, Long departmentId, Long id);

	List<WorkLog> findEmployeesByDepartmentId(Long departmentId);

	//Optional<WorkLog> findByEmail(String email);

	//void deleteByEmail(String email);
}