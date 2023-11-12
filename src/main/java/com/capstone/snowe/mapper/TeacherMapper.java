package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.TeacherDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {

    /*
    * 강사 정보 수정
    * */
    void updateTeacherStat(TeacherDTO teacherDTO);
}
