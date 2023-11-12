package com.capstone.snowe.service;

import com.capstone.snowe.dto.TeacherDTO;

public interface TeacherService {

    /*
     * 강사 정보 수정
     * */
    void updateTeacherStat(TeacherDTO teacherDTO);
}
