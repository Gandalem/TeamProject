package com.Team_Project.member.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Team_Project.entity.Member;
import com.Team_Project.member.repository.MemberRepository;
import com.Team_Project.member.service.MypageService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {
    
    private final MypageService mypageService;
    private final MemberRepository memberRepository;

    @GetMapping("/mypage")
    public ModelAndView showMyPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        ModelAndView mav = new ModelAndView("member/mypage");
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid email."));
        mav.addObject("member", member);
        return mav;
    }

    @PostMapping("/update")
    public String updateMemberInfo(@ModelAttribute Member updatedMember, HttpServletRequest request) {
        mypageService.updateMemberInfo(updatedMember);

        return "redirect:/";
    }


}