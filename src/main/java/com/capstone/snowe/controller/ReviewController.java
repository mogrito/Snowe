package com.capstone.snowe.controller;

import com.capstone.snowe.dto.ReviewDTO;
import com.capstone.snowe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/addReview")
    public void addReview(@RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal UserDetails user) {
        reviewDTO.setStudentId(user.getUsername());

        System.out.println("들어오는 리뷰DTO=> " + reviewDTO);
        this.reviewService.addReview(reviewDTO);
    }

    @GetMapping("/getReview")
    public List<ReviewDTO> getReview(@RequestParam String teacherId){
//        ReviewDTO reviewDTO = new ReviewDTO();
////        reviewDTO.setLessonId(lessonId);
//        reviewDTO.setTeacherId(teacherId);
        System.out.println(this.reviewService.getReview(teacherId));
        return this.reviewService.getReview(teacherId);
    }

}
