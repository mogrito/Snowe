package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    void addReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getReview(String teacherId);

}
