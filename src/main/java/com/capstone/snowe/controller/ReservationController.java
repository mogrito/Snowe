package com.capstone.snowe.controller;

import com.capstone.snowe.dto.ReservationDTO;
import com.capstone.snowe.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    private ReservationDTO reservationDTO;

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

    @GetMapping("/listOnDate")
    public ResponseEntity<ReservationDTO> getReservationListOnDate(@RequestParam String lessonDate, @AuthenticationPrincipal UserDetails user){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setStudentId(user.getUsername());
        reservationDTO.setLessonDate(lessonDate);

        ReservationDTO result = this.reservationService.getReservationListOnDate(reservationDTO);

        if (result != null && result.getReservations() != null && !result.getReservations().isEmpty()) {
            // 예약 목록이 비어있지 않으면 200 OK와 함께 데이터를 반환
            return ResponseEntity.ok(result);
        } else {
            // 예약 목록이 비어있거나 오류가 발생한 경우 204 No Content 반환
            return ResponseEntity.noContent().build();
        }
    }

}
