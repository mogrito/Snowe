package com.capstone.snowe.controller;

import com.capstone.snowe.dto.TeacherDTO;
import com.capstone.snowe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    /*
     * 관리자 페이지
     * */
    @GetMapping(value = "/adminpage", produces = "text/html")
    public String adminPage(Model model) {
        List<TeacherDTO> teachers = adminService.getApply();
        List<TeacherDTO> teachersN = adminService.getApplyN();

        model.addAttribute("teachers", teachers);
        model.addAttribute("teachersN", teachersN);

        return "admin";
    }

    /*
     * 강사 추가하기
     * */

    @PostMapping("/addteacher")
    public ResponseEntity<String> addTeachers(@RequestParam String loginId) throws Exception {
        // teacher테이블에 추가
        this.adminService.addTeacher(loginId);
        return ResponseEntity.ok("강사 설정 완료");
    }
    /*
     * 전체 신청 목록
     * */


    @GetMapping("/getApply")
    public List<TeacherDTO> getApply(){
        return this.adminService.getApply();
    }

    /*
     * 승인 되지 않은 신청 목록
     * */
    @GetMapping("/getApplyN")
    public List<TeacherDTO> getApplyN(){
        return this.adminService.getApplyN();
    }
}
