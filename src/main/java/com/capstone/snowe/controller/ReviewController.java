package com.capstone.snowe.controller;

import com.capstone.snowe.dto.ReviewDTO;
import com.capstone.snowe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
    private final ReviewService reviewService;

    @PostMapping("/addReview")
    public void addReview(@RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal UserDetails user) {
        reviewDTO.setStudentId(user.getUsername());
        logger.info("reviewDTO" + reviewDTO);

        this.reviewService.addReview(reviewDTO);
    }

    @GetMapping("/getReview")
    public List<ReviewDTO> getReview(@RequestParam String teacherId){
        logger.info("teacherId" + teacherId);

        return this.reviewService.getReview(teacherId);
    }

}
