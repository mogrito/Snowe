package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.dto.TeacherDTO;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService , UserDetailsService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String loginId){
        System.out.println(loginId);
        System.out.println(memberMapper.findByLoginId(loginId));
        MemberDTO user = memberMapper.findByLoginId(loginId);

        if (user == null) {
            // user가 null인 경우 예외 발생
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
        }
        System.out.println(user.getLoginId());
        System.out.println(user.getAuthorities());
        // 유저의 권한을 설정하는 부분
        return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(), user.getAuthorities());

    }
    public UserDetails me(@AuthenticationPrincipal UserDetails user){
        MemberDTO member = memberMapper.findByLoginId(user.getUsername());
        System.out.println("member ==> "+ member);
        // 아래 정보를 제외한 정보는 null 처리
        MemberDTO memberInfo = new MemberDTO();
        // 이름
        memberInfo.setName(member.getName());
        // 닉네임
        memberInfo.setNickname(member.getNickname());
        memberInfo.setBirthday(member.getBirthday());
        memberInfo.setEmail(member.getEmail());
        memberInfo.setGender(member.getGender());
        memberInfo.setRole(member.getRole());

        return memberInfo;
    }

    @Override
    public void apply(TeacherDTO teacherDTO,@AuthenticationPrincipal UserDetails user) {
        MemberDTO member = memberMapper.findByLoginId(user.getUsername());
        teacherDTO.setLoginId(member.getLoginId());
        teacherDTO.setName(member.getName());
        System.out.println(teacherDTO);
        memberMapper.apply(teacherDTO);
    }

    @Override
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

    // 모든 강사 정보 불러오기
    // 2024.02.11 redis cache 적용
    @Override
    @Cacheable(value = "TeacherDTO", key = "#ridingClass", cacheManager = "cacheManager", unless = "#ridingClass==''")
    public List<TeacherDTO> getAllTeacher(String ridingClass) {
        System.out.println("강사조회서비스 호출");
        return memberMapper.getAllTeacher(ridingClass);
    }

    @Override
    public List<TeacherDTO> getBoardTeacher(String ridingClass) {
        return memberMapper.getBoardTeacher(ridingClass);
    }

    @Override
    public List<TeacherDTO> getSkiTeacher(String ridingClass) {
        return memberMapper.getSkiTeacher(ridingClass);
    }

}
