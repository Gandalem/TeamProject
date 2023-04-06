package com.Team_Project.cList.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Team_Project.entity.Commute;
import com.Team_Project.entity.Member;


public interface CommuteRepository extends JpaRepository<Commute, Long>{
	
	// DB에 저장된 출, 퇴근 시간의 마지막 레코드를 가져오기
	Commute findFirstByOrderByIdxDesc();
	
	
	
    @Query("SELECT c.today FROM Commute c WHERE c.employee.idx = :employeeIdx")
    List<LocalDateTime> findTodayByEmployeeIdx(@Param("employeeIdx") Long employeeIdx);
	

	
}
