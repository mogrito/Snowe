package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private int reviewId;
    private String loginId;
    private String teacherId;
    private String lessonId;
    private int rating;
    private String reviewComment;
    private LocalDate regDate;

}