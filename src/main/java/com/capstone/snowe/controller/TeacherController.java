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
    @PostMapping("/teachers/{id}")
    public ResponseEntity<String> addTeachers(@PathVariable String id) throws Exception {
        // teacher테이블에 추가
        this.teacherService.addTeacher(id);
        /*
        * teacher테이블에 추가 후
        * member테이블의 per_code(회원구분)을 1(강사)로 변경
        * */
        this.teacherService.perCodeUpdate(id);

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
