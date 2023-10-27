package com.capstone.snowe.controller;

import com.capstone.snowe.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /*
     * 1. 캘린더에서 날짜를 선택
     * 2. 맘에 드는 강사의 강습을 선택 후 예약하기
     * */
    @PostMapping("/reserve")
    public ResponseEntity<String> lessonReservation(@RequestParam String lessonId, @RequestParam String teacherId, @RequestParam String studentId) throws Exception {

        this.reservationService.LessonReservationByDay(lessonId,teacherId,studentId);
        this.reservationService.updateReserveCountAndLessonStat(lessonId);

        return ResponseEntity.ok("예약 완료");
    }

}
