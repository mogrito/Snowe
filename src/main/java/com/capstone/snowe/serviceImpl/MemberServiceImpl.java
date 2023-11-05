package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService , UserDetailsService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

//    @Override
//    public MemberDTO login(String loginId, String password) {
//        return null;
//    }

    @Override
    public UserDetails loadUserByUsername(String loginId){
        MemberDTO user = memberMapper.findByLoginId(loginId);

        if (user == null) {
            // user가 null인 경우 예외 발생
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
        }
        System.out.println(user);
        System.out.println(user.getAuthorities());
        // 유저의 권한을 설정하는 부분
        return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(), user.getAuthorities());

    }
    public MemberDTO checkLogin(String username, String password) throws LoginException {
        MemberDTO user = memberMapper.findByLoginId(username);

        if (user == null) {
            // user가 null인 경우 예외 발생
            throw new LoginException("유저를 찾을 수 없습니다.");
        }
        // password 암호화

        // password check
        if(!password.equals(user.getPassword()))
            throw new LoginException("password error");

        return user;
    }

    @Transactional
    public MemberDTO signup(MemberDTO params) {
        if (memberMapper.findByLoginId(params.getLoginId()) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(params.getPassword());
        params.setPassword(encodedPassword);

        // 사용자 정보 저장
        memberMapper.signup(params);

        return params;
    }
    @Override
    public MemberDTO findMemberByLoginId(String loginId) {
        return memberMapper.findByLoginId(loginId);
    }

    @Override
    public int countMemberByLoginId(String loginId) {
        return memberMapper.countByLoginId(loginId);
    }

    @Override
    public int checkNickname(String nickname) {
        return memberMapper.checkNickname(nickname);
    }

    private void encodePassword(MemberDTO params) {
        params.encodingPassword(passwordEncoder);
    }
}
