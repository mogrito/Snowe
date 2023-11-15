package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.TeacherDTO;
import com.capstone.snowe.mapper.AdminMapper;
import com.capstone.snowe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    @Override
    public void addTeacher(String loginId) {
        this.adminMapper.addTeacher(loginId);
    }

    @Override
    public List<TeacherDTO> getApply() {
        return this.adminMapper.getApply();
    }

    @Override
    public List<TeacherDTO> getApplyN() {
        return this.adminMapper.getApplyN();
    }

}
