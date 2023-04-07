package com.Team_Project.member.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Team_Project.entity.Member;
import com.Team_Project.entity.MemberType;
import com.Team_Project.member.dto.MemberDTO;
import com.Team_Project.member.repository.MemberRepository;
import com.Team_Project.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/admin")
    public String adminSignupForm() {
        return "member/admin_signup";
    }

    @GetMapping("/user")
    public String userSignupForm() {
        return "member/user_signup";
    }

    @PostMapping(value = "/admin")
    public String adminSignup(MemberDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        
        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        
        member.setSample6_postcode(memberDTO.getSample6_postcode());
        member.setSample6_address(memberDTO.getSample6_address());
        member.setSample6_detailAddress(memberDTO.getSample6_detailAddress());
        member.setSample6_extraAddress(memberDTO.getSample6_extraAddress());
        member.setPhoneNumber(memberDTO.getPhoneNumber());
        member.setMemberType(MemberType.ADMIN);
        memberService.register(member);
        return "redirect:/login"; // 회원가입 완료 페이지로 이동
    }


    @PostMapping(value = "/user")
    public String userSignup(MemberDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        
        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        
        member.setSample6_postcode(memberDTO.getSample6_postcode());
        member.setSample6_address(memberDTO.getSample6_address());
        member.setSample6_detailAddress(memberDTO.getSample6_detailAddress());
        member.setSample6_extraAddress(memberDTO.getSample6_extraAddress());
        member.setPhoneNumber(memberDTO.getPhoneNumber());
        member.setMemberType(MemberType.EMPLOYEE);
        memberService.register(member);
        return "redirect:/login"; // 회원가입 완료 페이지로 이동
    }

    @PostMapping(value = "/checkEmail")
    @ResponseBody
    public String checkEmail(@RequestParam("email") String email) {    	
    	
        Optional<Member> memberOpt = memberRepository.findByEmail(email);        
        
        if (memberOpt.isPresent()) {
            return "duplicate"; 
        } else {
            return ""; 
        }
    }
    
   //Employee 에서 등록시 멤버 정보 업데이트 처리
    @PutMapping("/memberUpdate")
    @ResponseBody
    public void updateMember(@RequestBody Member member) {
    	System.out.println("확인용" + member);
    	this.memberService.updateMember(member);
    }
    
    //Employee 에서 삭제시 멤버 정보 업데이트 처리
    @PutMapping("/memberUpdate2")
    @ResponseBody
    public void updateMember2(@RequestBody List<String> emails) {
    	this.memberService.updateMember2(emails);
    }
    
    
    
    
    
    
}