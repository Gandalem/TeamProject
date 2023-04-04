package com.Team_Project.member.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Team_Project.entity.Member;
import com.Team_Project.member.repository.MemberRepository;
import com.Team_Project.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MypageController {
    
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
    
    @PutMapping("/update")
    public ModelAndView updateMemberInfo(@RequestParam("id") Long id,
                                          @RequestParam("name") String name,
                                          @RequestParam("email") String email,
                                          @RequestParam("phoneNumber") String phoneNumber,
                                          @RequestParam("sample6_postcode") String sample6_postcode,
                                          @RequestParam("sample6_address") String sample6_address,
                                          @RequestParam("sample6_detailAddress") String sample6_detailAddress,
                                          @RequestParam("sample6_extraAddress") String sample6_extraAddress) {
       
    	System.out.println("id ==> : " + id);
    	System.out.println("name ==> : " + name);
    	System.out.println("email ==> : " + email);
    	System.out.println("phoneNumber ==> : " + phoneNumber);
    	
    	
    	Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid id."));
        member.setName(name);
        member.setEmail(email);
        member.setPhoneNumber(phoneNumber);
        member.setSample6_postcode(sample6_postcode);
        member.setSample6_address(sample6_address);
        member.setSample6_detailAddress(sample6_detailAddress);
        member.setSample6_extraAddress(sample6_extraAddress);

        memberRepository.save(member);

        ModelAndView mav = new ModelAndView("member/mypage");
        mav.addObject("member", member);
        mav.addObject("message", "회원 정보가 업데이트 되었습니다.");
        return mav;
    }
    
    @GetMapping("/member/{id}")
    @ResponseBody
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }


}