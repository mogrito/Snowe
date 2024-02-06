package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.TeacherDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
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
