package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.TeacherDTO;
import com.capstone.snowe.mapper.TeacherMapper;
import com.capstone.snowe.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public void updateTeacherStat(TeacherDTO teacherDTO) {
        System.out.println("수정할 데이터 = " + teacherDTO);
        this.teacherMapper.updateTeacherStat(teacherDTO);
    }
}
