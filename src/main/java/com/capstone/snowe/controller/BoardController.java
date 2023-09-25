package com.capstone.snowe.controller;

import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping (value = "/board/main")
    public String home(Model model) {

        List<BoardDTO> boardList = boardService.getBoardList();
//        System.out.println(boardList.);
        model.addAttribute("boardList", boardList);

        return "board/boardMain";
    }

    @GetMapping(value = "/board/boardWrite")
    public String boardWrite() {
        return "board/boardWrite";
    }
    @PostMapping(value ="/board/boardSave")
    public String add(BindingResult result, RedirectAttributes rea) throws Exception {
        if (result.hasErrors()) {
            rea.addAllAttributes(result.getModel());
            return "redirect:list";
        }

        if (this.boardService.addBoard(new BoardDTO()) == 1) {
            rea.addFlashAttribute("message", "신규 등록 완료");
            return "redirect:list";
        } else {
            rea.addFlashAttribute("message", "신규 등록 실패!");
            return "redirect:list";
        }
    }
}
