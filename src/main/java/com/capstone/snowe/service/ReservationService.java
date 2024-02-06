package com.capstone.snowe.service;

import com.capstone.snowe.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    /*
     * 1. 캘린더에서 날짜를 선택
     * 2. 맘에 드는 강사의 강습을 선택 후 예약하기
     * */
    void LessonReservationByDay(ReservationDTO reservationDTO);


    ReservationDTO getReservationListOnDate(ReservationDTO reservationDTO);

    List<ReservationDTO> reservationDetail(ReservationDTO reservationDTO);

    void cancelReservation(ReservationDTO reservationDTO);
}
