package com.capstone.snowe.controller;

import com.capstone.snowe.dto.TeacherDTO;
import com.capstone.snowe.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* 리액트, 부트 연동 */
@CrossOrigin
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /*
    * 강사 추가하기
    * */
    @PostMapping("/teachers")
    public ResponseEntity<String> addTeachers(@RequestBody TeacherDTO teacherDTO) throws Exception {

        this.teacherService.addTeacher(teacherDTO);
        //this.teacherService.perCodeUpdate(memberResponse);

        return ResponseEntity.ok("강사 설정 완료");
    }

    /*
    * 강사 정보 수정하기(스키장 정보)
    * */
    @PutMapping("/teachers/{id}")
    public ResponseEntity<String> updateTeacherStat(@PathVariable String id, @RequestBody TeacherDTO teacherDTO) throws Exception {
        teacherDTO.setId(id);
        this.teacherService.updateTeacherStat(teacherDTO);
        return ResponseEntity.ok("정보가 수정되었습니다.");
    }
}
