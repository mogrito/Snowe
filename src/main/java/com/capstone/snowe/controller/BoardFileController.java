package com.capstone.snowe.controller;

import com.capstone.snowe.service.BoardFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BoardFileController {
    @Autowired
    private BoardFileService boardFileService;
}
