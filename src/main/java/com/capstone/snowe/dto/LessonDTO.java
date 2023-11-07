package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {


    private String loginId;
    private Date lessonDate;
    private String lessonStat;
    private String lessonId;
    private String lessonDiv;
    private int reserveCount;
    private int maxReserveCount;
    private String title;
    private String lessonClass;
    private String lessonLevel;
    private Date delDate;
    private String delYn;
    private Date lessonRegDate;
}
