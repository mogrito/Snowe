package com.capstone.snowe.controller;

import com.capstone.snowe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    /*
     * 강사 추가하기
     * */
    @PostMapping("/addteacher")
    public ResponseEntity<String> addTeachers(@RequestParam String loginId) throws Exception {
        // teacher테이블에 추가
        this.adminService.addTeacher(loginId);
        return ResponseEntity.ok("강사 설정 완료");
    }
}
