package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.MemberRequestDto;
import com.capstone.snowe.dto.MemberResponseDto;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.service.MemberService;
import com.capstone.snowe.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public MemberResponseDto login(final String loginId, final String password) {

        MemberResponseDto member = findMemberByLoginId(loginId);
        if (member == null || !passwordEncoder.matches(password, member.getPassword())) {
            return null; // 로그인 실패
        }
        member.clearPassword();
        return member;
    }

    @Override
    @Transactional
    public String saveMember(final MemberRequestDto params) {
        params.encodingPassword(passwordEncoder);
        System.out.println(params);
        memberMapper.save(params);
        return params.getLoginId();
    }

    @Override
    public MemberResponseDto findMemberByLoginId(final String loginId) {
        return memberMapper.findByLoginId(loginId);
    }

    @Override
    @Transactional
    public String updateMember(final MemberRequestDto params) {
        params.encodingPassword(passwordEncoder);
        memberMapper.update(params);
        return params.getLoginId();
    }

    @Override
    public int countMemberByLoginId(final String loginId) {
        return memberMapper.countByLoginId(loginId);
    }

    @Override
    public int checkNickname(final String nickname) {
        return memberMapper.checkNickname(nickname);
    }
}
