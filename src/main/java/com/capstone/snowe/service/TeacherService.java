package com.capstone.snowe.service;

import com.capstone.snowe.domain.MemberResponse;
import com.capstone.snowe.dto.TeacherDTO;

public interface TeacherService {

    /*
     * 관리자가 강사를 추가
     * */
    void addTeacher(TeacherDTO teacherDTO);

    /*
     * 강사가 추가되면 member테이블의 perCode를 수정
     * */
    void perCodeUpdate(MemberResponse memberResponse);

    /*
     * 강사 정보 수정
     * */
    void updateTeacherStat(TeacherDTO teacherDTO);
}
