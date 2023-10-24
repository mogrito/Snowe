package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.LoginRequestDto;
import com.capstone.snowe.dto.LoginResponseDto;
import com.capstone.snowe.dto.RegisterRequestDto;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto login(String loginId, String password) {
        // 1. 회원 정보 조회
        LoginResponseDto member = findMemberByLoginId(loginId);

        // 2. 회원 정보가 없을 경우 또는 비밀번호가 틀릴 경우 처리
        if (member == null || !passwordEncoder.matches(password, member.getPassword())) {
            throw new NullPointerException("로그인 실패: 회원 정보를 찾을 수 없습니다.");
        }

        // 3. 회원 응답 객체에서 비밀번호를 제거한 후 회원 정보 리턴
        member.clearPassword();
        return member;
    }

    @Override
    @Transactional
    public String saveMember(RegisterRequestDto params) {
            params.encodingPassword(passwordEncoder);
            System.out.println(params);
            memberMapper.save(params);
            return params.getLoginId();
    }

    @Override
    public LoginResponseDto findMemberByLoginId(String loginId) {
        return memberMapper.findByLoginId(loginId);
    }

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
//        System.out.println(loginId);
//        return memberMapper.countByLoginId(loginId);
//    }
//
//    public int checkNickname(final String nickname) {
//        System.out.println(nickname);
//        return memberMapper.checkNickname(nickname);}
}
