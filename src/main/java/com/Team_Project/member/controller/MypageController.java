package com.Team_Project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Team_Project.entity.Member;
import com.Team_Project.member.Service.MypageService;
import com.Team_Project.member.dto.MemberDTO;

@Controller
public class MypageController {
    @Autowired
    private MypageService mypageService;

    @GetMapping("/mypage")
    public String myPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Member member = mypageService.getMemberByEmail(userEmail);

        if (member != null) {
            model.addAttribute("member", member);
            return "mypage";
        } else {
            return "error";
        }
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute MemberDTO memberDTO) {
        Member updatedMember = mypageService.updateMemberInfo(memberDTO);
        if (updatedMember != null) {
            return "redirect:/mypage";
        } else {
            return "error";
        }
    }

    @GetMapping("/change-password")
    public String changePasswordPage() {
        return "change_password";
    }

    @PostMapping("/update-password")
    public String updatePassword(String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Member updatedMember = mypageService.updatePassword(userEmail, newPassword);
        if (updatedMember != null) {
            return "redirect:/mypage";
        } else {
            return "error";
        }
    }
    
    @PostMapping("/delete-account")
    public String deleteAccount(Long memberId) {
    	mypageService.deleteAccount(memberId);
        return "redirect:/logout";
    }
}