package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {
    private String LessonId;
    private String loginId;
    private String title;
    private String lessonClass;
    private String lessonLevel;
    private Date lessonRegDate;
    private Date lessonDate;
    private String lessonStat;
    private String lessonDiv;
    private int reserveCount;
    private int maxReserveCount;
    private Date delDate;
    private String delYn;

}
