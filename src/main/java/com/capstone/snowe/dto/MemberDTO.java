package com.capstone.snowe.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
public class MemberDTO implements UserDetails {

    private String loginId;                // 로그인 ID(PK)
    private String password;               // 비밀번호
    private String name;                   // 이름
    private String gender;                 // 성별
    private String birthday;            // 생년월일
    private String nickname;
    private String role;
    private Collection<? extends GrantedAuthority> authorities;
    private LocalDateTime createdDate;     // 생성일시
    private LocalDateTime modifiedDate;    // 최종 수정일시

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        // 여러 권한을 가지고 있다면 반복해서 authorities에 추가
        // authorities.add(new SimpleGrantedAuthority(anotherRole));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부를 설정
        return true; // 현재는 계정 만료를 체크하지 않음
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금 여부를 설정
        return true; // 현재는 계정 잠금을 체크하지 않음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명 정보(비밀번호) 만료 여부를 설정
        return true; // 현재는 자격 증명 정보 만료를 체크하지 않음
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부를 설정
        return true; // 현재는 계정 활성화를 체크하지 않음
    }

    public void encodingPassword(PasswordEncoder passwordEncoder) {
        if (StringUtils.isEmpty(password)) {
            return;
        }

        password = passwordEncoder.encode(password);
        System.out.println("encoder="+password);
    }
}