package com.capstone.snowe.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

    /*
     * 1. 캘린더에서 날짜를 선택
     * 2. 맘에 드는 강사의 강습을 선택 후 예약하기
     * */
    void LessonReservationByDay(String lessonId, String teacherId, String studentId);

    /*
     * 예약 완료 후 적용
     *
     * lesson테이블의 reserveCount가 업데이트
     * reserveCount가 maxReserveCount랑 같거나 크다면
     * lessonStat를 변경
     * */
    void updateReserveCountAndLessonStat(String lessonId);

    /*
    *
    * */

}
