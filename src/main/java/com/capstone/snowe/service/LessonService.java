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
    void lessonUpdate(LessonDTO lessonDTO, @AuthenticationPrincipal UserDetails user);

    /*
     * 강습 삭제하기
     * */
    void lessonDel(LessonDTO lessonDTO, @AuthenticationPrincipal UserDetails user);

    /*
     * 해당하는 날짜에 존재하는 강습 정보 리스트 가져오기
     * */
    List<LessonJoinDTO> ableLessonListByDay(String lessonDate);

    // 토큰id랑 lesson의 loginId랑 일치 시 로직진행
    int selectLoginIdByLessonId(LessonDTO lessonDTO, @AuthenticationPrincipal UserDetails user);
}
