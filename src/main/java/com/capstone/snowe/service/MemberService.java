package com.capstone.snowe.service;

import com.capstone.snowe.dto.MemberRequestDto;
import com.capstone.snowe.dto.MemberResponseDto;

// MemberService.java (인터페이스)
public interface MemberService {
    MemberResponseDto login(String loginId, String password);

    String saveMember(MemberRequestDto params);

    MemberResponseDto findMemberByLoginId(String loginId);

    String updateMember(MemberRequestDto params);

    int countMemberByLoginId(String loginId);

    int checkNickname(String nickname);

}