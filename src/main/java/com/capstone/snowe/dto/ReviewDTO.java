package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private String reviewId;
    private String loginId;
    private String teacherId;
    private String studentId;
    private String lessonId;
    private String review;
    private LocalDate reviewDate;
    private String lessonTitle;
}