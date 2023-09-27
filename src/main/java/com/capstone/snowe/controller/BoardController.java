package com.capstone.snowe.controller;

import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/* 리액트, 부트 연동 */
//@CrossOrigin
//@RestController
@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    /* 게시글 목록 조회기능 */
    @GetMapping (value = "/board/list")
    public String list(Model model) {

        List<BoardDTO> boardList = boardService.getBoardList();
        System.out.println("컨트롤러 : " + boardList);
        model.addAttribute("boardList", boardList);
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
        BoardDTO board = boardService.getBoardNo(bno);
        model.addAttribute("board", board);

        return "board/view";
    }

    /* 게시글 수정 페이지로 이동 후 페이지 수정 */
    @RequestMapping(value = "/board/editPage")
    public String editPage(@RequestParam(value="BNO", required = false, defaultValue = "0")int bno, Model model) throws Exception {

        BoardDTO board = boardService.getBoardNo(bno);      // 수정할 게시글을 특정하기 위해 bno가져오기
        model.addAttribute("board", board);     //
        System.out.println("테스트" + board);
        return "board/editPage";
    }
    /* 게시글 수정하기 */
    @PostMapping(value = "/board/edit")
    public String edit(@ModelAttribute("BoardDTO")@Valid BoardDTO boardDTO) throws Exception {

        boardService.editBoard(boardDTO);

        return "redirect:/board/list";
    }

    /* 게시글 삭제하기 */
    @PostMapping(value = "/board/del")
    public String del(@RequestParam(value="BNO", required = false, defaultValue = "0")int bno) throws Exception {
        boardService.delBoard(bno);
        return "redirect:/board/list";
    }



}
