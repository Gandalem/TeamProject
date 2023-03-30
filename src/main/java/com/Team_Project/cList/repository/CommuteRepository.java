package com.Team_Project.cList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Team_Project.entity.Commute;


public interface CommuteRepository extends JpaRepository<Commute, Long>{
	
	// DB에 저장된 출, 퇴근 시간의 마지막 레코드를 가져오기
	Commute findFirstByOrderByIdxDesc();
}
