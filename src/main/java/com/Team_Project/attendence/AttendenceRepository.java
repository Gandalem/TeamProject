package com.Team_Project.attendence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Team_Project.entity.Commute;

public interface AttendenceRepository extends JpaRepository<Commute, Long>{

	List<Commute> findAllBy();

}
