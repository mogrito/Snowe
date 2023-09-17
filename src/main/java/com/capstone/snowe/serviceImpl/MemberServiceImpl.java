package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.domain.Member;
import com.capstone.snowe.dto.MemberFormDTO;
import com.capstone.snowe.repository.MemberRepository;
import com.capstone.snowe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // MemberRepository 생성자 사용안함

public class MemberServiceImpl implements MemberService {
    private  final MemberRepository memberRepository;

    @Override
    public  Long join(MemberFormDTO memberFormDTO){
        Member member = Member.builder()
                .email(memberFormDTO.getEmail())
                .username(memberFormDTO.getUsername())
                .password(memberFormDTO.getPassword())
                .build();
        return memberRepository.save(member).getId();
    }
}
