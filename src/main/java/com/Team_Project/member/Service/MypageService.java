package com.Team_Project.member.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Team_Project.entity.Member;
import com.Team_Project.member.dto.MemberDTO;
import com.Team_Project.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageService {
    
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }

    public Member updateMemberInfo(MemberDTO memberDTO) {
        Member member = memberRepository.findByEmail(memberDTO.getEmail()).orElse(null);
        if (member != null) {
            member.setName(memberDTO.getName());
            member.setSample6_postcode(memberDTO.getSample6_postcode());
            member.setSample6_address(memberDTO.getSample6_address());
            member.setSample6_detailAddress(memberDTO.getSample6_detailAddress());
            member.setSample6_extraAddress(memberDTO.getSample6_extraAddress());
            //member.setPhoneNumber(memberDTO.getPhoneNumber());
            return memberRepository.save(member);
        } else {
            return null;
        }
    }

    public Member updatePassword(String email, String newPassword) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        if (member != null) {
            member.setPassword(passwordEncoder.encode(newPassword));
            return memberRepository.save(member);
        } else {
            return null;
        }
    }
    public void deleteAccount(Long memberId) {
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        memberOpt.ifPresent(member -> {
            member.setDeleted(true); // 회원 상태를 변경
            member.setDeletedDate(LocalDateTime.now());
            memberRepository.save(member);
        });
    }

}
	