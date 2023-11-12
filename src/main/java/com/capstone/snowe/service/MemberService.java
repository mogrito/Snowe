package com.capstone.snowe.service;

import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.dto.TeacherDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface MemberService {


    UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException;

    UserDetails me(UserDetails userDetails);

    MemberDTO signup(MemberDTO params);

    void apply(MemberDTO memberDTO,@AuthenticationPrincipal UserDetails user);

    MemberDTO findMemberByLoginId(String loginId);

    int countMemberByLoginId(String loginId);

    int checkNickname(String nickname);

    List<TeacherDTO> getAllTeacher (String ridingClass);

    List<TeacherDTO> getBoardTeacher (String ridingClass);

    List<TeacherDTO> getSkiTeacher (String ridingClass);
}