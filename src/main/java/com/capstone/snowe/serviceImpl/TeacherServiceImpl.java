package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.LessonDTO;
import com.capstone.snowe.dto.TeacherDTO;
import com.capstone.snowe.mapper.TeacherMapper;
import com.capstone.snowe.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper teacherMapper;

    /*
     * 강사 정보 수정
     * */
    @Override
    public void updateTeacherStat(TeacherDTO teacherDTO) {
        System.out.println("수정할 데이터 = " + teacherDTO);
        this.teacherMapper.updateTeacherStat(teacherDTO);
    }

    /*
     * 강사가 본인의 강습 정보
     *
     * */
    @Override
    public List<LessonDTO> lessonDetail(String loginId) {
        return this.teacherMapper.lessonDetail(loginId);
    }

    /*
     * 강습 신청 인원
     *
     * */
    @Override
    public List<LessonDTO> studentByLessonId(LessonDTO lessonDTO) {
        return this.teacherMapper.studentByLessonId(lessonDTO);
    }
}
