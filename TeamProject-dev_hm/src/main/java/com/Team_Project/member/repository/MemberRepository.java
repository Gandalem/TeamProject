package com.Team_Project.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Team_Project.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
    boolean existsByEmail(String email);
    
    Optional<Member> findByEmail(String email);
    
}