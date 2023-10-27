package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.domain.MemberVo;
import com.capstone.snowe.dto.MemberResponseDto;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username을 기반으로 사용자 정보 조회
        MemberResponseDto memberDto = memberMapper.findByLoginId(username);

        if (memberDto == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        // 사용자의 권한을 SimpleGrantedAuthority로 변환
        List<GrantedAuthority> authorities = memberDto.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        // UserDetails 생성
        MemberVo member = MemberVo.builder()
                .email(memberDto.getLoginId())
                .password(memberDto.getPassword()) // 패스워드는 암호화되어야 합니다
                .roles(authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .build();

        return member;
    }
}