package com.capstone.snowe.service;

import com.capstone.snowe.dto.LoginResponseDto;
import com.capstone.snowe.dto.RegisterRequestDto;
import com.capstone.snowe.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


public interface MemberService {
    LoginResponseDto login(String loginId, String password);

    String saveMember(RegisterRequestDto params);

    LoginResponseDto findMemberByLoginId(String loginId);

//    String updateMember(MemberRequest params);
//
//    int countMemberByLoginId(String loginId);
//
//    int checkNickname(String nickname);
}