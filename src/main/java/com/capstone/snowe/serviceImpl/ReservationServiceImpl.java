package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.mapper.ReservationMapper;
import com.capstone.snowe.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    /*
    * 1. 캘린더에서 날짜를 선택
    * 2. 맘에 드는 강사의 강습을 선택 후 예약하기
    * */
    @Override
    public void LessonReservationByDay(String lessonId, String teacherId, String studentId) {
        this.reservationMapper.LessonReservationByDay(lessonId, teacherId, studentId);
    }

    /*
    * 예약 완료 후 적용
    *
    * lesson테이블의 reserveCount가 업데이트
    * reserveCount가 maxReserveCount랑 같거나 크다면
    * lessonStat를 변경
    * */
    @Override
    public void updateReserveCountAndLessonStat(String lessonId) {
        this.reservationMapper.updateReserveCountAndLessonStat(lessonId);
    }
}
