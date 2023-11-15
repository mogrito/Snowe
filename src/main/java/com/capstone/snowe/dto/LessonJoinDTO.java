package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonJoinDTO {

    private String loginId;
    private String name;
    private String lessonDate;
    private String lessonDateEnd;
    private String lessonStat;
    private String lessonId;
    private String Div;
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
    private String lessonIntroduce;
}
