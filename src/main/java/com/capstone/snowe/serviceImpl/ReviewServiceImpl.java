package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.mapper.ReviewMapper;
import com.capstone.snowe.service.ReivewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReivewService {

    private final ReviewMapper reviewMapper;
    private final MemberMapper memberMapper;



}
