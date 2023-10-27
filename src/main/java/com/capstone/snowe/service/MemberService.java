package com.capstone.snowe.service;

import com.capstone.snowe.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.security.auth.login.LoginException;


public interface MemberService {
//    MemberDTO login(String loginId, String password);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    MemberDTO checkLogin(String username,String password) throws LoginException;

    String saveMember(MemberDTO params);

    MemberDTO findMemberByLoginId(String loginId);

    int countMemberByLoginId(String loginId);

    int checkNickname(String nickname);
}