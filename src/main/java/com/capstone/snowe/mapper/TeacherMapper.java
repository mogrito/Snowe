package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.LessonDTO;
import com.capstone.snowe.dto.TeacherDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {

    /*
    * 강사 정보 수정
    * */
    void updateTeacherStat(TeacherDTO teacherDTO);

    /*
    * 강사가 본인의 강습정보
    *
    * */
    List<LessonDTO> lessonDetail(String loginId);

    /*
     * 강습 신청 인원
     *
     * */
    List<LessonDTO> studentByLessonId(LessonDTO lessonDTO);
}
