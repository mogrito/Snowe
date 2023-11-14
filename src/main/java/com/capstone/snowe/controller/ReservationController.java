package com.capstone.snowe.controller;

import com.capstone.snowe.dto.ReservationDTO;
import com.capstone.snowe.service.ReservationService;
import lombok.RequiredArgsConstructor;
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

    private final ReservationService reservationService;

    /*
     * 1. 캘린더에서 날짜를 선택
     * 2. 맘에 드는 강사의 강습을 선택 후 예약하기
     * */
    @PostMapping("/reserve")
    public ResponseEntity<String> lessonReservation(@RequestBody ReservationDTO reservationDTO, @AuthenticationPrincipal UserDetails user) throws Exception {
        reservationDTO.setStudentId(user.getUsername());
        this.reservationService.LessonReservationByDay(reservationDTO);

        return ResponseEntity.ok("예약 완료");
    }

    @GetMapping("/listOnDate")
    @ResponseBody
    public ReservationDTO getReservationListOnDate(@RequestParam String lessonDate, @AuthenticationPrincipal UserDetails user){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setStudentId(user.getUsername());
        reservationDTO.setLessonDate(lessonDate);

        return this.reservationService.getReservationListOnDate(reservationDTO);
    }

    @GetMapping("/reserveList")
    public List<ReservationDTO> getreservelist(@AuthenticationPrincipal UserDetails user){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setStudentId(user.getUsername());
        System.out.println("예약DTO:" +reservationDTO);
        return this.reservationService.reservationDetail(reservationDTO);
    }
    @PostMapping("/reserveCancel")
    public void cancelReserve(@RequestParam String reserveId, @AuthenticationPrincipal UserDetails user){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setStudentId(user.getUsername());
        reservationDTO.setReserveId(reserveId);
        System.out.println(reserveId +"예약 취소");
        this.reservationService.cancelReservation(reservationDTO);
    }
}
