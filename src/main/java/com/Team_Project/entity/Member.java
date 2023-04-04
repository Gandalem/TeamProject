package com.Team_Project.entity;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "members")
@Data
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    
    private String sample6_postcode;
    private String sample6_address;
    private String sample6_detailAddress;
    private String sample6_extraAddress;
    
    private String phoneNumber;
    private String profilePicture;
    private LocalDateTime registrationDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private boolean deleted;
    
    
    // 이름으로 부서가져오기1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    
    // 이름으로 부서가져오기2
    public String getDepartmentName() {
        if (department != null) {
            return department.getDname();
        }
        return "";
    }

    // 이름으로 부서가져오기3
    public String getCompanyName() {
        if (department != null) {
            Company company = department.getCompany();
            if (company != null) {
                return company.getCname();
            }
        }
        return "";
    }
    
    

    // 회원 타입 (관리자, 일반 유저)
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + memberType.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !deleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !deleted;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !deleted;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }

	public MemberType getMemberType() {
    return this.memberType;
}

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}

	public Member orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}