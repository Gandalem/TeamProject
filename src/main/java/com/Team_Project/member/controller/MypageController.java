package com.Team_Project.member.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Team_Project.entity.Member;
import com.Team_Project.member.repository.MemberRepository;
import com.Team_Project.member.service.MemberService;
import com.Team_Project.member.service.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {
    
    private final MypageService mypageService;
    private final MemberRepository memberRepository;
    private final MemberService memberService; 

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
    @ResponseBody
    public String updateMemberInfo(@RequestParam("id") Long id,
                                   @RequestParam("name") String name,
                                   @RequestParam("email") String email,
                                   @RequestParam("phoneNumber") String phoneNumber,
                                   @RequestParam("sample6_postcode") String postcode,
                                   @RequestParam("sample6_address") String address,
                                   @RequestParam("sample6_detailAddress") String detailAddress,
                                   @RequestParam("sample6_extraAddress") String extraAddress) {
        
        Member member = memberService.findById(id); // ID로 회원 정보를 가져옴
        if (member == null) {
            return "FAIL"; // 회원 정보가 없을 경우 실패 반환
        }
        
        // 회원 정보 업데이트
        member.setName(name);
        member.setEmail(email);
        member.setPhoneNumber(phoneNumber);
        member.setSample6_postcode(postcode);
        member.setSample6_address(address);
        member.setSample6_detailAddress(detailAddress);
        member.setSample6_address(extraAddress);
        
        memberService.save(member); // 업데이트된 회원 정보 저장
        
        return "SUCCESS"; // 성공 반환
    }

}