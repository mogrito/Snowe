package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.MemberRequestDto;
import com.capstone.snowe.dto.MemberResponseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    /**
     * 회원 정보 저장 (회원가입)
     * @param params - 회원 정보
     */
    void save(MemberRequestDto params);

    /**
     * 회원 상세정보 조회
     * @param loginId - PK
     * @return 회원 상세정보
     */
    MemberResponseDto findByLoginId(String loginId);

    /**
     * 회원 정보 수정
     * @param params - 회원 정보
     */
    void update(MemberRequestDto params);



    /**
     * 회원 수 카운팅 (ID 중복 체크)
     * @param loginId - PK
     * @return 회원 수
     */
    int countByLoginId(String loginId);

    int checkNickname(String nickname);
}
