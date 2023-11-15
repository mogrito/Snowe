package com.capstone.snowe.service;

import com.capstone.snowe.dto.TeacherDTO;

import java.util.List;

public interface AdminService {

    /*
     * 관리자가 강사를 추가
     * */
    void addTeacher(String loginId);

    /*
     * 전체 신청 목록
     * */
    List<TeacherDTO> getApply();

    /*
     * 승인 되지 않은 신청 목록
     * */
    List<TeacherDTO> getApplyN();
}
