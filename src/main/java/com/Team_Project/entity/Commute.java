package com.Team_Project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Commute {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	// 출근
	private LocalDateTime workStart;
	
	// 퇴근
	private LocalDateTime workEnd;
	
	// 오늘 날짜
	private LocalDateTime today;
	
	// 어느 회원이 출,퇴근했는지
	@ManyToOne
	@JoinColumn(name = "employee")
	private Employee employee;
	

}
