package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.dto.CommentDTO;

import java.util.List;

public interface CommentMapper {

    List<CommentDTO> getCommentByBoard(int BOARD_ID);       //해당하는 게시글에 대한 댓글 목록

    void addComment(CommentDTO commentDTO);      // 댓글 추가(부모)

    CommentDTO getCommentId(int COMMENT_ID);    // 댓글번호 가져오기

    int editComment(CommentDTO commentDTO);        // 댓글 수정하기

    int delComment(int COMMENT_ID);                  // 댓글 삭제
}
