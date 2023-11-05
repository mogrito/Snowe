package com.capstone.snowe.service;

import com.capstone.snowe.dto.MemberDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface MemberService {

    UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException;

//    MemberDTO checkLogin(String username,String password) throws LoginException;

    UserDetails me(UserDetails userDetails);

    MemberDTO signup(MemberDTO params);

    MemberDTO findMemberByLoginId(String loginId);

    int countMemberByLoginId(String loginId);

    int checkNickname(String nickname);
}