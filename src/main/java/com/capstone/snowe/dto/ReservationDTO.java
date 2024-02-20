package com.capstone.snowe.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDTO {

    private String reserveId;

    private String name;

    private String teacherId;

    private String studentId;

    private String lessonId;

    private String lessonCode;

    private String resortId;

    private LocalDate reserveDate;

    private LocalDate cancelDate;

    private String lessonStart;

    private String lessonEnd;

    private String cancelStat;

    private String lessonDate;

    private String lessonDateEnd;

    private String lessonTitle;
}
