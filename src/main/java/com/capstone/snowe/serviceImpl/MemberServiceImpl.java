package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

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
        System.out.println(loginId);
        if (user == null) {
            // user가 null인 경우 예외 발생
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
        }

        // 유저의 권한을 설정하는 부분
        return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(), new ArrayList<>());

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




    @Override
    @Transactional
    public String saveMember(MemberDTO params) {
        encodePassword(params);
        memberMapper.save(params);
        return params.getLoginId();
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