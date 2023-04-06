package com.Team_Project.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        member.setPassword(memberDTO.getPassword());
        member.setPhoneNumber(memberDTO.getPhoneNumber());
        member.setSample6_postcode(memberDTO.getSample6_postcode());
        member.setSample6_address(memberDTO.getSample6_address());
        member.setSample6_detailAddress(memberDTO.getSample6_detailAddress());
        member.setSample6_extraAddress(memberDTO.getSample6_extraAddress());
        this.memberRepository.save(member);
        return member;
    }

    public boolean isEmailDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public void save(Member member, String name, String email, String phoneNumber, String sample6_postcode, String sample6_address, String sample6_detailAddress, String sample6_extraAddress) {
        member.setName(name);
        member.setEmail(email);
        member.setPhoneNumber(phoneNumber);
        member.setSample6_postcode(sample6_postcode);
        member.setSample6_address(sample6_address);
        member.setSample6_detailAddress(sample6_detailAddress);
        member.setSample6_extraAddress(sample6_extraAddress);
        this.memberRepository.save(member);
    }
    
    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + email));
    }


    
}