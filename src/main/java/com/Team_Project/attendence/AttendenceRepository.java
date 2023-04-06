package com.Team_Project.attendence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Team_Project.entity.Commute;

public interface AttendenceRepository extends JpaRepository<Commute, Long>{


}
