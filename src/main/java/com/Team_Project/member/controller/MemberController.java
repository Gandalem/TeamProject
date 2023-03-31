package com.Team_Project.member.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Team_Project.entity.Member;
import com.Team_Project.entity.MemberType;
import com.Team_Project.member.Service.MemberService;
import com.Team_Project.member.dto.MemberDTO;
import com.Team_Project.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/admin")
    public String adminSignupForm() {
        return "member/admin_signup";
    }

    @GetMapping("/user")
    public String userSignupForm() {
        return "member/user_signup";
    }

    @PostMapping("/admin")
    public String adminSignup(MemberDTO memberDTO) {
        Member member = new Member();
        member.setMemberType(MemberType.ADMIN);
        memberService.register(member);
        return "redirect:/"; // 회원가입 완료 페이지로 이동
    }

    @PostMapping("/user")
    public String userSignup(MemberDTO memberDTO) {
        Member member = new Member();
        member.setMemberType(MemberType.EMPLOYEE);
        memberService.register(member);
        return "redirect:/";
    }
    
    @PostMapping("/check-email")
    public ResponseEntity<String> checkEmail(@RequestBody MemberDTO memberDTO) {
        Optional<Member> memberOpt = memberRepository.findByEmail(memberDTO.getEmail());
        if (memberOpt.isPresent()) {
            return ResponseEntity.ok("duplicate");
        } else {
            return ResponseEntity.ok("");
        }
    }
 
    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@RequestBody MemberDTO memberDTO) {
        String email = memberDTO.getEmail();
        if (memberService.emailExists(email)) {
            return new ResponseEntity<>("duplicate_email", HttpStatus.BAD_REQUEST);
        } else if (memberRepository.findByEmail(email).isPresent()) {
            return new ResponseEntity<>("duplicate_email", HttpStatus.BAD_REQUEST);
        } else {
            Member member = new Member();
            member.setName(memberDTO.getName());
            member.setEmail(email);
            member.setPassword(memberDTO.getPassword());
            member.setAddress(memberDTO.getAddress());
            member.setMemberType(MemberType.EMPLOYEE);
            memberService.register(member);
            return new ResponseEntity<>("member_created", HttpStatus.OK);
        }
    }
    
}
