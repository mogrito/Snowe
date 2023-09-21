package com.capstone.snowe.controller;

import com.capstone.snowe.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/board/main", method = RequestMethod.GET)
    public String home() {
        return "board/boardMain";
    }

    @GetMapping(value = "/board/boardWrite")
    public String boardWrite() {
        return "board/boardWrite";
    }
}
