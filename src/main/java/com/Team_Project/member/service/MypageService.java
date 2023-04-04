package com.Team_Project.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Team_Project.entity.Member;
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

    public void updateMemberInfo(Member updatedMember) {
        Member member = memberRepository.findByEmail(updatedMember.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email."));
        member.setName(updatedMember.getName());
        member.setPhoneNumber(updatedMember.getPhoneNumber());
        member.setSample6_postcode(updatedMember.getSample6_postcode());
        member.setSample6_address(updatedMember.getSample6_address());
        member.setSample6_detailAddress(updatedMember.getSample6_detailAddress());
        member.setSample6_extraAddress(updatedMember.getSample6_extraAddress());

        memberRepository.save(member);
    }

}
	