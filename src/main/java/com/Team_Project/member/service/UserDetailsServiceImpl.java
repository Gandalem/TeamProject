package com.Team_Project.member.service;
//package com.Team_Project.member.Service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.Team_Project.entity.Member;
//import com.Team_Project.member.repository.MemberRepository;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // Member 타입의 Optional 객체를 가져옴
//        java.util.Optional<Member> optionalMember = memberRepository.findByEmail(email);
//
//        // Optional 객체를 Member 타입으로 변환
//        Member member = optionalMember.orElseThrow(() -> new UsernameNotFoundException("Invalid email or password."));
//
//        return new User(member.getEmail(), member.getPassword(), getAuthorities(member));
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(Member member) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        String role = member.getMemberType().toString();
//        authorities.add(new SimpleGrantedAuthority(role));
//        return authorities;
//    }
//}
