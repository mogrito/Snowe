package com.capstone.snowe.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDTO {

    private String reserveId;
    private String teacherId;
    private String studentId;
    private String lessonId;
    private String lessonCode;
    private LocalDate reserveDate;
    private LocalDate cancelDate;
    private String cancelStat;
    private String lessonDate;
}
