package com.Team_Project.entity;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
    
    @OneToMany(mappedBy = "member")
    private List<WorkLog> workLogList;
    
    
    @OneToOne
   	private Company company;
    
    @OneToOne
    private Department department;
    
    
    
    
    
    
    
    
    
    
    
    
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

    public boolean isAdmin() {
        return memberType == MemberType.ADMIN;
    }
}


