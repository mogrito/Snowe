package com.capstone.snowe.service;

import com.capstone.snowe.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    
    List<CommentDTO> getCommentByBoard(int BOARD_ID);       //댓글 목록

    void addComment(CommentDTO commentDTO);      //댓글 추가(부모)

    CommentDTO getCommentId(int BOARD_ID);    // 댓글번호 가져오기

    void editComment(CommentDTO commentDTO);        // 댓글 수정하기

    int delComment(int COMMENT_ID);                  // 댓글 삭제
}
