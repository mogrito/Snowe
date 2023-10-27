package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonJoinDTO {

    private String lessonId;
    private String loginId;
    private String name;
    private String title;
    private String lessonClass;
    private String lessonLevel;
    private Date lessonDate;
    private int reserveCount;
    private int maxReserveCount;
    private String div;
    private String stat;
}
