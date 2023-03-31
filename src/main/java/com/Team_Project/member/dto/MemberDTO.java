package com.Team_Project.member.dto;

import lombok.Data;

@Data
public class MemberDTO {
	
    private String name;
    private String email;
    
    private String password;
    private String confirmPassword;
    private String address;

    // getters and setters
}