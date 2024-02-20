package com.capstone.snowe.controller;

import com.capstone.snowe.dto.ReservationDTO;
import com.capstone.snowe.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private static final Logger logger = LoggerFactory.getLogger(LessonController.class);
    private final ReservationService reservationService;

    /*
     * 1. 캘린더 에서 날짜를 선택
     * 2. 맘에 드는 강사의 강습을 선택 후 예약 하기
     * */
    @PostMapping("/reserve")
    public ResponseEntity<String> lessonReservation(@RequestBody ReservationDTO reservationDTO, @AuthenticationPrincipal UserDetails user) throws Exception {
        reservationDTO.setStudentId(user.getUsername());
        this.reservationService.LessonReservationByDay(reservationDTO);

        logger.info("reservationDTO : " + reservationDTO);

        return ResponseEntity.ok("예약 완료");
    }
    /*
     * 날짜별 강습 가져오기
     * */
    @GetMapping("/listOnDate")
    @ResponseBody
    public ReservationDTO getReservationListOnDate(@RequestParam String lessonDate, @AuthenticationPrincipal UserDetails user){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setStudentId(user.getUsername());
        reservationDTO.setLessonDate(lessonDate);

        return this.reservationService.getReservationListOnDate(reservationDTO);
    }
    /*
     * 본인 예약 내역
     * */
    @GetMapping("/reserveList")
    public List<ReservationDTO> getreservelist(@AuthenticationPrincipal UserDetails user){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setStudentId(user.getUsername());

        System.out.println("reservationDTO:" +reservationDTO);

        return this.reservationService.reservationDetail(reservationDTO);
    }
    /*
     * 예약 취소
     * */
    @PostMapping("/reserveCancel")
    public void cancelReserve(@RequestParam String reserveId, @AuthenticationPrincipal UserDetails user){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setStudentId(user.getUsername());
        reservationDTO.setReserveId(reserveId);
        logger.info(reserveId + "취소");

        this.reservationService.cancelReservation(reservationDTO);
    }
}
