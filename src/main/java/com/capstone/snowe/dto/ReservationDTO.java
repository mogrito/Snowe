package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private String reserveId;
    private String teacherId;
    private String studentId;
    private String lessonId;
    private String lessonCode;
    private Date reserveDate;
    private Date cancleDate;
    private String cancleStat;

}
