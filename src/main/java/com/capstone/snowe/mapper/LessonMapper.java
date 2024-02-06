package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.LessonDTO;
import com.capstone.snowe.dto.LessonJoinDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LessonMapper {
    
    /*
    * 강사가 강습 등록하기
    * */
    void lessonInsert(LessonDTO lessonDTO);

    /*
    * 강습정보 수정하기
    * */
    void lessonUpdate(LessonDTO lessonDTO);

    /*
    * 강습 삭제하기
    * */
    void lessonDel(LessonDTO lessonDTO);

    /*
    * 해당하는 날짜에 존재하는 강습 정보 리스트 가져오기
    * */
    List<LessonJoinDTO> ableLessonListByDay(@Param("lessonDate") String lessonDate);

    // 토큰id랑 lesson의 loginId랑 일치 시 로직진행
    int selectLoginIdByLessonId(LessonDTO lessonDTO);
    
}
