package com.capstone.snowe.service;

import com.capstone.snowe.dto.LoginResponseDto;
import com.capstone.snowe.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    /**
     * 로그인
     * @param loginId - 로그인 ID
     * @param password - 비밀번호
     * @return 회원 상세정보
     */
    public LoginResponseDto login(final String loginId, final String password) {

        // 1. 회원 정보 및 비밀번호 조회
        LoginResponseDto member = findMemberByLoginId(loginId);

        // 2. 회원 정보 및 비밀번호 체크
        if (member == null){
            return null;
        }

        // 3. 회원 응답 객체에서 비밀번호를 제거한 후 회원 정보 리턴
        return member;
    }

    /**
     * 회원 정보 저장 (회원가입)
     * @param params - 회원 정보
     * @return PK
     */
    @Transactional
    public String saveMember(final LoginResponseDto params) {
        System.out.println(params);
        memberMapper.save(params);
        return params.getLoginId();
    }

    /**
     * 회원 상세정보 조회
     * @param loginId - UK
     * @return 회원 상세정보
     */
    public LoginResponseDto findMemberByLoginId(final String loginId) {
        return memberMapper.findByLoginId(loginId);
    }
//
//    /**
//     * 회원 정보 수정
//     * @param params - 회원 정보
//     * @return PK
//     */
//    @Transactional
//    public String updateMember(final MemberRequest params) {
//        params.encodingPassword(passwordEncoder);
//        memberMapper.update(params);
//        return params.getLoginId();
//    }
//
//
//    /**
//     * 회원 수 카운팅 (ID 중복 체크)
//     * @param loginId - UK
//     * @return 회원 수
//     */
//    public int countMemberByLoginId(final String loginId) {
//        return memberMapper.countByLoginId(loginId);
//    }
}