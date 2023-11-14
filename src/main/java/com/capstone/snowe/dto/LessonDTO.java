package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {


    private String loginId;
    private String lessonDate;
    private String lessonDateEnd;
    private String lessonStat;
    private String lessonId;
    private String lessonDiv;
    private int reserveCount;
    private int maxReserveCount;
    private String lessonTitle;
    private String lessonClass;
    private String lessonLevel;
    private String lessonStart;
    private String lessonEnd;
    private LocalDate delDate;
    private String delYn;
    private LocalDate lessonRegDate;
    private String lessonAge;
    private String lessonIntroduce;
}
