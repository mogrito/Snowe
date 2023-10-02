package com.capstone.snowe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);


    /*
    * 
    * 게시판 전체 리스트 조회
    * 
    * */
    @GetMapping ("/board/list")
    public List<BoardDTO> list(Model model) throws Exception {

        //게시글의 전체개수
        int boardCount = this.boardService.boardCount();

        //

        return boardService.getBoardList();
    }
    

    /*
    * 
    * 게시글 작성 API
    * 
    * */
    @PostMapping("/board/add")
    public ResponseEntity<String> add(@RequestBody BoardDTO boardDTO) throws Exception {

        try {
            this.boardService.addBoard(boardDTO);
            return ResponseEntity.ok("SUCCESS");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    /*
    * 
    * 게시글 자세히보기 API
    * 
    * */
    // @RequestParam int BOARD_ID
    @GetMapping("/board/view/{BOARD_ID}")
    public ResponseEntity<BoardDTO> getBoardView(@PathVariable int BOARD_ID) throws Exception {
        BoardDTO board = boardService.getBoardId(BOARD_ID);

        //model.addAttribute("board", board);

        return ResponseEntity.ok(board);
    }

    /*
    *
    * 게시글 수정 API
    *
    * */
    @PutMapping("/board/edit/{BOARD_ID}")
    public ResponseEntity<String> editPage(@PathVariable int BOARD_ID, @RequestBody BoardDTO requestBoard) throws Exception {

        BoardDTO boardDTO = this.boardService.getBoardId(BOARD_ID);
        logger.debug("Received update request for BOARD_ID={}", BOARD_ID);

        // 가져온 데이터가 없으면 404 에러 반환
        if (boardDTO == null) {
            return ResponseEntity.notFound().build();
        }

        // 변경사항 적용
        boardDTO.setTitle(requestBoard.getTitle());
        boardDTO.setContent(requestBoard.getContent());

        //데이터 저장
        boardService.editBoard(boardDTO);

        logger.debug("Employee updated: {}", boardDTO);

        return ResponseEntity.ok("수정완료");

    }

    /*
    *
    * 게시글 삭제 API
    *
    * */
    // ("/board/del")
    //@RequestParam(value="BNO", required = false, defaultValue = "0")
    @DeleteMapping("/board/del/{BOARD_ID}")
    public ResponseEntity<String> del(@PathVariable int BOARD_ID) throws Exception {
        boardService.delBoard(BOARD_ID);
        return ResponseEntity.ok("삭제성공");
    }

    /*
    *
    * 제목으로 게시글 검색하기
    *
    * */
    @GetMapping("/board/search")                        // 클라이언트에서 searchTitle 요청
    public ResponseEntity<List<BoardDTO>> searchBoard(@RequestParam("searchTitle") String TITLE) {
        List<BoardDTO> searchResult = this.boardService.searchBoard(TITLE);
        return ResponseEntity.ok(searchResult);
    }

    /*
    *
    *
    *
    * */


}
