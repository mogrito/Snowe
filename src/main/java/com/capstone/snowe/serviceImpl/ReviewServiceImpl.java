package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.ReviewDTO;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.mapper.ReviewMapper;
import com.capstone.snowe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final MemberMapper memberMapper;


    @Override
    public void addReview(ReviewDTO reviewDTO) {
        this.reviewMapper.addReview(reviewDTO);
    }

    @Override
    public List<ReviewDTO> getReview(String teacherId) {
        return this.reviewMapper.getReview(teacherId);
    }
}
