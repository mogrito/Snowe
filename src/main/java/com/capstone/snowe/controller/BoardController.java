package com.capstone.snowe.controller;

import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    /* 게시글 목록 조회기능 */
    @GetMapping (value = "/board/list")
    public String home(Model model) {

        List<BoardDTO> boardList = boardService.getBoardList();
//        System.out.println(boardList.);
        model.addAttribute("boardList", boardList);
        System.out.println(boardList);
        return "board/list";
    }


    /* 게시글 작성 페이지로 이동 */
    @GetMapping(value = "/board/write")
    public String boardWrite() {
        return "board/write";
    }
    /* 게시글 insert문 실행 */
    @PostMapping(value ="/board/add")
    public String add(@Valid BoardDTO boardDTO, BindingResult result, RedirectAttributes rea) throws Exception {
        if (result.hasErrors()) {
            rea.addAllAttributes(result.getModel());
            return "redirect:/board/list";
        }
//        boardService.addBoard(boardDTO);

        int output = this.boardService.addBoard(boardDTO);

        if (output > 0) {
            rea.addFlashAttribute("message", "신규 등록 완료");
            return "redirect:/board/list";
        } else {
            rea.addFlashAttribute("message", "신규 등록 실패!");
            return "redirect:/board/list";
        }
    }

    /* 게시글 상세보기 기능 */
    @GetMapping(value = "/board/view/{bno}")
    public String getBoardView(@PathVariable int bno, Model model) throws Exception {
        BoardDTO board = boardService.getBoardView(bno);
        model.addAttribute("board", board);

        return "board/view";
    }

}
