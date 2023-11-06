package com.capstone.snowe.service;

import com.capstone.snowe.dto.LessonDTO;
import com.capstone.snowe.dto.LessonJoinDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface LessonService {

    /*
    * 강사가 강습 등록
    * */
    void lessonInsert(LessonDTO lessonDTO);

    /*
     * 강습정보 수정하기
     * */
    void lessonUpdate(LessonDTO lessonDTO);

    /*
     * 강습 삭제하기
     * */
    void lessonDel(String lessonId);

    /*
     * 해당하는 날짜에 존재하는 강습 정보 리스트 가져오기
     * */
    List<LessonJoinDTO> ableLessonListByDay(String lessonDate);
    /*
     * 테스트용 강습리스트
     * */
    List<LessonJoinDTO> lessonList();
}
