package com.Team_Project.cList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Team_Project.entity.Department;

public interface DRepository extends JpaRepository<Department, Long> {

}
