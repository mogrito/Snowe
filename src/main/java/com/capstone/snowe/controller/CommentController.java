package com.capstone.snowe.controller;

import com.capstone.snowe.dto.CommentDTO;
import com.capstone.snowe.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* 리액트, 부트 연동 */
@CrossOrigin
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    /*
    * 
    * 댓글 리스트 불러오기
    * 
    * */
    @GetMapping("/comment/list/{boardId}")
    public List<CommentDTO> getCommentByBoard(@PathVariable int boardId) throws Exception {
        return this.commentService.getCommentByBoard(boardId);
    }

    /*
    *
    * 댓글 작성(부모)
    *
    * */
    @PostMapping("/board/view/{boardId}/comment")
    public ResponseEntity<String> addComment(@PathVariable int boardId, @RequestBody CommentDTO commentDTO) throws Exception {

        try {
            commentDTO.setBoardId(boardId);
            this.commentService.addComment(commentDTO);
            return ResponseEntity.ok("댓글작성완료");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        /*commentDTO.setBOARD_ID(BOARD_ID);
        this.commentService.addComment(commentDTO);
        return "redirect:write";*/
    }
    @GetMapping("/comment/write")
    public String add() {
        return "board/comment_form";
    }

    /*
    *
    * 댓글 수정
    *
    * */
    @PutMapping("/comment/edit/{commentId}")
    public ResponseEntity<String> editComment(@PathVariable int commentId, @RequestBody CommentDTO commentDTO) throws  Exception {

        try {
            commentDTO.setCommentId(commentId);
            this.commentService.editComment(commentDTO);
            return ResponseEntity.ok("댓글수정완료");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    /*    CommentDTO commentDTO = this.commentService.getCommentId(commentId);

        // 데이터x 시 에러
        *//*if (commentDTO == null) {
            return ResponseEntity.notFound().build();
        }*//*

        // 변경사항 적용
        commentDTO.setCONTENT(requestComment.getCONTENT());

        //저장
        commentService.editComment(commentDTO);

        return ResponseEntity.ok("댓글 수정 완료");
    }*/

    /*
    *
    * 댓글 삭제
    *
    * */
    @PutMapping("/comment/del/{commentId}")
    public ResponseEntity<String> delComment(@PathVariable int commentId) throws Exception {
        commentService.delComment(commentId);
        return ResponseEntity.ok("댓글 삭제 성공");
    }


}
