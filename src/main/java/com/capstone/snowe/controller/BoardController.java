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
@CrossOrigin
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


        return boardService.getBoardList();
    }
    /*
     *
     * 게시판 전체 리스트 조회 (오래된 순)
     *
     * */
    @GetMapping ("/board/oldlist")
    public List<BoardDTO> oldSortlist(Model model) throws Exception {

        return boardService.oldGetBoardList();
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
            return ResponseEntity.ok("작성완료");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    /*
    * 
    * 게시글 자세히보기 API
    * 조회수 증가까지 함께
    * 
    * */
    // @RequestParam int BOARD_ID
    @GetMapping("/board/view/{BOARD_ID}")
    public ResponseEntity<BoardDTO> getBoardView(@PathVariable int BOARD_ID) throws Exception {
        BoardDTO board = boardService.getBoardId(BOARD_ID);

        //클릭 시 조회수 증가
        boardService.increaseViewCount(BOARD_ID);

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
    * 논리적 삭제로 구현
    *
    * */
    // ("/board/del")
    //@RequestParam(value="BNO", required = false, defaultValue = "0")
    @PutMapping("/board/del/{BOARD_ID}")
    public ResponseEntity<String> del(@PathVariable int BOARD_ID) throws Exception {
        boardService.delBoard(BOARD_ID);
        return ResponseEntity.ok("삭제성공");
    }

    /*
    *
    * 제목, 내용, 작성자 기준으로 게시글 검색하기
    *
    * */
    @GetMapping("/board/search")                        // 클라이언트에서 검색타입을 맞추고 해당 키워드 요청
    public ResponseEntity<List<BoardDTO>> searchBoard(@RequestParam("searchType") String searchType, @RequestParam("keyword") String keyword) {
        List<BoardDTO> searchResult = this.boardService.searchBoard(searchType, keyword);
        return ResponseEntity.ok(searchResult);
    }

    /*
    *
    * 추천수 증가 API
    *
    * */
    @PostMapping("/board/recommend/{BOARD_ID}")
    public ResponseEntity<String> recommendBoard(@PathVariable int BOARD_ID) {
        try {
            boardService.increaseRecommendCount(BOARD_ID);
            return ResponseEntity.ok("추천증가");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/board/comment/{BOARD_ID}")
    public ResponseEntity<String> commentCount(@PathVariable int BOARD_ID) {
        try {
            boardService.increaseCommentCount(BOARD_ID);
            return ResponseEntity.ok("댓글개수 수정");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
