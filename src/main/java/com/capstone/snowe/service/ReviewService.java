package com.capstone.snowe.service;

import com.capstone.snowe.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    void addReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getReview(String teacherId);
}
