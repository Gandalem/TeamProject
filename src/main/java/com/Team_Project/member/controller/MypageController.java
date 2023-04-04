package com.Team_Project.member.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Team_Project.entity.Member;
import com.Team_Project.member.dto.MemberDTO;
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
        // SecurityContext에서 인증된 사용자의 정보를 가져옵니다.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        // 조회한 데이터를 ModelAndView에 담아서 마이페이지로 전달합니다.
        ModelAndView mav = new ModelAndView("member/mypage");
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid email."));
        mav.addObject("member", member);
        return mav;
    }

    @PostMapping("/mypage/update")
    public String updateMemberInfo(@ModelAttribute Member updatedMember, HttpServletRequest request) {
        // 로그인된 사용자의 정보를 수정
        Member member = (Member) request.getSession().getAttribute("loginUser");
        member.setName(updatedMember.getName());
        member.setEmail(updatedMember.getEmail());
        member.setPhoneNumber(updatedMember.getPhoneNumber());
        member.setSample6_postcode(updatedMember.getSample6_postcode());
        member.setSample6_address(updatedMember.getSample6_address());
        member.setSample6_detailAddress(updatedMember.getSample6_detailAddress());
        member.setSample6_extraAddress(updatedMember.getSample6_extraAddress());

        memberRepository.save(member); // 변경사항 저장

        return "redirect:/"; // "/" 경로로 리다이렉트
    }


}