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
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    /*
    * 
    * 댓글 리스트 불러오기
    * 
    * */
    @GetMapping("/comment/list")
    public List<CommentDTO> getCommentByBoard() throws Exception {
        return this.commentService.getCommentByBoard();
    }

    /*
    *
    * 댓글 작성(부모)
    *
    * */
    /*@PostMapping("/board/view/{BOARD_ID}/comment")
    public ResponseEntity<String> addComment(@RequestParam int BOARD_ID, @RequestParam String CONTENT) throws Exception {
        try {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setContent();
            CommentDTO
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }*/

    /*
    *
    * 댓글 수정
    *
    * */
    @PutMapping("/comment/1/{COMMENT_ID}")
    public ResponseEntity<String> editComment(@PathVariable int COMMENT_ID, @RequestBody CommentDTO requsetComment) throws  Exception {

        CommentDTO commentDTO = this.commentService.getCommentId(COMMENT_ID);

        // 데이터x 시 에러
        /*if (commentDTO == null) {
            return ResponseEntity.notFound().build();
        }*/

        // 변경사항 적용
        commentDTO.setContent(requsetComment.getContent());

        //저장
        commentService.editComment(commentDTO);

        return ResponseEntity.ok("댓글 수정 완료");
    }

    /*
    *
    * 댓글 삭제
    *
    * */
    @PutMapping("/comment/1/{COMMENT_ID}")
    public ResponseEntity<String> delComment(@PathVariable int COMMENT_ID) throws Exception {
        commentService.delComment(COMMENT_ID);
        return ResponseEntity.ok("댓글 삭제 성공");
    }


}
