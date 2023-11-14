package com.capstone.snowe.service;

import com.capstone.snowe.dto.LessonDTO;
import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

    /*
     * 강사 정보 수정
     * */
    void updateTeacherStat(TeacherDTO teacherDTO);

    /*
     * 강사가 본인의 강습 정보
     *
     * */
    List<LessonDTO> lessonDetail(String loginId);

    /*
     * 강습 신청 인원
     *
     * */
    List<MemberDTO> studentByLessonId(MemberDTO memberDTO);
}
