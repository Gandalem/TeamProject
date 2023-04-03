package com.Team_Project.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Team_Project.entity.Member;
import com.Team_Project.member.dto.MemberDTO;
import com.Team_Project.member.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	
    private final MemberRepository memberRepository;

    public void register(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
        memberRepository.save(member);
    }
    
    
	/*
	 * public boolean emailExists(String email) { return
	 * memberRepository.findByEmail(email) != null; }
	 */
 
    public Member createMember(MemberDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setPassword(memberDTO.getPassword());
        member.setSample6_postcode(memberDTO.getSample6_postcode());
        member.setSample6_address(memberDTO.getSample6_address());
        member.setSample6_detailAddress(memberDTO.getSample6_detailAddress());
        member.setSample6_extraAddress(memberDTO.getSample6_extraAddress());
        return memberRepository.save(member);
    }

    public boolean isEmailDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }



    
}