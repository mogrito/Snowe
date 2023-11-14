package com.capstone.snowe.controller;

import com.capstone.snowe.dto.LessonDTO;
import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.dto.TeacherDTO;
import com.capstone.snowe.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/me")
    public void me() {
        System.out.println("강사권한확인");
    }
    /*
    * 강사 정보 수정하기(스키장 정보)
    * */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTeacherStat(@PathVariable String id, @RequestBody TeacherDTO teacherDTO) throws Exception {

        teacherDTO.setLoginId(id);
        this.teacherService.updateTeacherStat(teacherDTO);

        return ResponseEntity.ok("정보가 수정되었습니다.");
    }

    /*
     * 강사가 본인의 강습 정보
     *
     * */
    @GetMapping("/lessonList")
    public List<LessonDTO> lessonDetail (@AuthenticationPrincipal UserDetails user) {
        String loginId = user.getUsername();
        return this.teacherService.lessonDetail(loginId);
    }

    /*
     * 강습 신청 인원
     *
     * */
    @GetMapping("/student-List")
    public List<MemberDTO> studentByLessonId(@RequestParam String lessonId, @AuthenticationPrincipal UserDetails user) {
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setStudentId(user.getUsername());
        memberDTO.setLessonId(lessonId);

        System.out.println(memberDTO);
        return this.teacherService.studentByLessonId(memberDTO);
    }
}
