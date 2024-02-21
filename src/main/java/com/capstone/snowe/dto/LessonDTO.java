package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {
                private String lessonId;
                private String loginId;
                private String name;
                private String lessonTitle;
                private String lessonClass;
                private String lessonLevel;
                private String lessonIntroduce;
                private String lessonDate;
                private String lessonDateEnd;
                private int reserveCount;
                private int maxReserveCount;
                private String lessonStart;
                private String lessonEnd;
                private String lessonDiv;
                private String lessonStat;
                private String resortId;
                private String delYn;
                private LocalDate delDate;
                private LocalDate lessonRegDate;
}
