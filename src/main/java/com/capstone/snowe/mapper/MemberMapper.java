package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.dto.TeacherDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    /**
     * 회원 정보 저장 (회원가입)
     *
     * @param params - 회원 정보
     */
    void signup(MemberDTO params);

    /**
     * 강사 자격 신청
     */
    void apply(MemberDTO member);


    /**
     * 회원 상세정보 조회
     * @param loginId - PK
     * @return 회원 상세정보
     */
    MemberDTO findByLoginId(String loginId);


    /**
     * 회원 수 카운팅 (ID 중복 체크)
     * @param loginId - PK
     * @return 회원 수
     */
    int countByLoginId(String loginId);

    int checkNickname(String nickname);

    /**
     * 항목별 강사리스트 받아오기
     */
    List<TeacherDTO> getAllTeacher(String ridingClass);

    List<TeacherDTO> getBoardTeacher(String ridingClass);

    List<TeacherDTO> getSkiTeacher(String ridingClass);
}
