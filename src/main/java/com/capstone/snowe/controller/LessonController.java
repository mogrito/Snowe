package com.capstone.snowe.controller;

import com.capstone.snowe.dto.LessonDTO;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;
    private final MemberMapper memberMapper;



    /*
    * 강사가 강습 등록하기
    * */
    @PostMapping("/add")
    public ResponseEntity<String> lessonInsert(@RequestBody LessonDTO lessonDTO, @AuthenticationPrincipal UserDetails user) throws Exception{
        lessonDTO.setLoginId(user.getUsername());

        String div = lessonDTO.getLessonDiv();
        System.out.println(lessonDTO);
        switch (div) {
            case "오전" -> lessonDTO.setLessonDiv("LD01");
            case "오후" -> lessonDTO.setLessonDiv("LD02");
            case "야간" -> lessonDTO.setLessonDiv("LD03");
            default -> {
            }
        }
        this.lessonService.lessonInsert(lessonDTO);
        return ResponseEntity.ok("강습등록이 완료되었습니다.");
    }

    /*
    * 강습정보 수정하기
    * */
    @PutMapping("/edit/{lessonId}")
    public ResponseEntity<?> lessonUpdate(@PathVariable String lessonId,@RequestBody LessonDTO lessonDTO, @AuthenticationPrincipal UserDetails user) throws Exception {

        LessonDTO checkDTO = new LessonDTO();
        //레슨id값 할당
        checkDTO.setLessonId(lessonId);

        int checkIdCount = this.lessonService.selectLoginIdByLessonId(checkDTO, user);
        System.out.println(checkIdCount);

        if (checkIdCount == 1 ) {

            lessonDTO.setLessonId(lessonId);

            System.out.println("lessonDTO는?? ==> "+lessonDTO);

            this.lessonService.lessonUpdate(lessonDTO, user);

            return ResponseEntity.ok("강습 수정 완료");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("강습 수정 실패");
    }

    /*
    * 강습 삭제하기
    * */
    @PutMapping("/del/{lessonId}")
    public ResponseEntity<String> lessonDel(@PathVariable String lessonId, @RequestBody LessonDTO lessonDTO, @AuthenticationPrincipal UserDetails user) throws Exception {
        LessonDTO checkDTO = new LessonDTO();
        //레슨id값 할당
        checkDTO.setLessonId(lessonId);

        int checkIdCount = this.lessonService.selectLoginIdByLessonId(checkDTO, user);
        System.out.println(checkIdCount);

        if (checkIdCount == 1 ) {

            lessonDTO.setLessonId(lessonId);

            System.out.println("lessonDTO는?? ==> "+lessonDTO);

            this.lessonService.lessonDel(lessonDTO, user);

            return ResponseEntity.ok("강습 삭제 완료");
        }
        else{
            ResponseEntity.status(401);
        }
        return ResponseEntity.ok("등록된 강습 삭제 완료");
    }



}
