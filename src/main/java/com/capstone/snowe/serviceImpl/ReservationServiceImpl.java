package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.ReservationDTO;
import com.capstone.snowe.mapper.ReservationMapper;
import com.capstone.snowe.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper reservationMapper;

    /*
     * 1. 캘린더에서 날짜를 선택
     * 2. 맘에 드는 강사의 강습을 선택 후 예약하기
     * */
    @Override
    public void LessonReservationByDay(ReservationDTO reservationDTO) {
        this.reservationMapper.LessonReservationByDay(reservationDTO);
    }

    @Override
    public ReservationDTO getReservationListOnDate(ReservationDTO reservationDTO) {
        return this.reservationMapper.reservationListOnDate(reservationDTO);

    }
}
