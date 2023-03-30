package com.Team_Project.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Team_Project.entity.Member;
import com.Team_Project.entity.MemberType;
import com.Team_Project.member.Service.MemberService;
import com.Team_Project.member.dto.MemberDTO;

@Controller
@RequestMapping("/signup")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/admin")
    public String adminSignupForm() {
        return "admin_signup";
    }

    @GetMapping("/user")
    public String userSignupForm() {
        return "user_signup";
    }

    @PostMapping("/admin")
    public String adminSignup(MemberDTO memberDTO) {
        Member member = new Member();
        member.setMemberType(MemberType.ADMIN);
        memberService.register(member);
        return "redirect:/";
    }

    @PostMapping("/user")
    public String userSignup(MemberDTO memberDTO) {
        Member member = new Member();
        member.setMemberType(MemberType.EMPLOYEE);
        memberService.register(member);
        return "redirect:/";
    }
    
    @PostMapping("/check-email")
    public ResponseEntity<String> checkEmail(@RequestParam String email) {
        if (memberService.emailExists(email)) {
            return new ResponseEntity<>("duplicate", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("unique", HttpStatus.OK);
        }
    }
 
    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@RequestBody MemberDTO memberDTO) {
        // validate MemberDTO fields
        if (memberService.emailExists(memberDTO.getEmail())) {
            return new ResponseEntity<>("duplicate_email", HttpStatus.BAD_REQUEST);
        } else {
            Member member = new Member();
            memberService.register(member);
            // return success response
            return new ResponseEntity<>("member_created", HttpStatus.OK);
        }
    }
    
}
