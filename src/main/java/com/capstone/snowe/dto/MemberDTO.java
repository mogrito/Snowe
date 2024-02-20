package com.capstone.snowe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String email;                  // 이메일

    private String gender;                 // 성별

    private String birthday;            // 생년월일

    private String nickname;

    private String role;
    @JsonIgnore
    private Collection<?> authorities;

    private LocalDateTime createdDate;     // 생성일시

    private LocalDateTime modifiedDate;    // 최종 수정일시


    // 비밀번호 암호화
    public void encodingPassword(PasswordEncoder passwordEncoder) {
        if (StringUtils.isEmpty(password)) {
            return;
        }
        password = passwordEncoder.encode(password);
    }

    public void clearPassword() {
        this.password = "";
    }

    // UserDetails 에서 제공 하는 권한 설정 , DB의 role 을 받아와 권한을 설정함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    // UserDetails 오버라이드
    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}