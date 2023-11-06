package com.capstone.snowe.controller;

import com.capstone.snowe.dto.LessonDTO;
import com.capstone.snowe.dto.LessonJoinDTO;
import com.capstone.snowe.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class LessonController {
    @Autowired
    private LessonService lessonService;

    /*
    * 강사가 강습 등록하기
    * */
    @PostMapping("/lesson/add")
    public ResponseEntity<String> lessonInsert(@RequestBody LessonDTO lessonDTO, @AuthenticationPrincipal UserDetails user) throws Exception{
        lessonDTO.setLoginId(user.getUsername());  //강사 아이디 받아
        this.lessonService.lessonInsert(lessonDTO);
        return ResponseEntity.ok("강습등록이 완료되었습니다.");
    }

    /*
    * 강습정보 수정하기
    * */
    @PutMapping("/lesson/edit{lessonId}")
    public ResponseEntity<String> lessonUpdate(@PathVariable String lessonId,@RequestBody LessonDTO lessonDTO ,@AuthenticationPrincipal UserDetails user) throws Exception {
        lessonDTO.setLessonId(lessonId);
        if(lessonDTO.getLoginId().equals(user.getUsername())) {
            this.lessonService.lessonUpdate(lessonDTO);
        }
        else{
            ResponseEntity.status(401);
        }
        return ResponseEntity.ok("강습 수정 완료!");
    }

    /*
    * 강습 삭제하기
    * */
    @PutMapping("/lesson/del/{lessonId}")
    public ResponseEntity<String> lessonDel(@PathVariable String lessonId, @RequestBody LessonDTO lessonDTO, @AuthenticationPrincipal UserDetails user) throws Exception {
        lessonDTO.setLoginId(lessonId);
        if(lessonDTO.getLoginId().equals(user.getUsername())) {
            this.lessonService.lessonUpdate(lessonDTO);
        }
        else{
            ResponseEntity.status(401);
        }
        return ResponseEntity.ok("등록된 강습 삭제 완료");
    }

    /*
     * 해당하는 날짜에 존재하는 강습 정보 리스트 가져오기
     * */
    @GetMapping("/lesson")
    public ResponseEntity<List<LessonJoinDTO>> ableLesson(@RequestParam("lessonDate") String lessonDate) throws Exception {
        List<LessonJoinDTO> lessonListByDay = lessonService.ableLessonListByDay(lessonDate);
        return ResponseEntity.ok(lessonListByDay);
    }

    /*
    * 테스트용 강습 리스트
    * */
    @GetMapping("/lesson/list")
    public List<LessonJoinDTO> lessonList() {
        return lessonService.lessonList();
    }
}
