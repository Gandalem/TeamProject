package com.Team_Project.cList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Team_Project.entity.Commute;
import com.Team_Project.entity.Member;


public interface CommuteRepository extends JpaRepository<Commute, Long>{
	
	// DB에 저장된 출, 퇴근 시간의 마지막 레코드를 가져오기
	Commute findFirstByOrderByIdxDesc();
	
	
	// 아래 오류
	// List<Commute> findByAuthor(Member author);
	
}
