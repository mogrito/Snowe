package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.CommentDTO;
import com.capstone.snowe.mapper.CommentMapper;
import com.capstone.snowe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override       //댓글 목록
    public List<CommentDTO> getCommentByBoard(int BOARD_ID) {

        System.out.println("댓글목록 : " + commentMapper.getCommentByBoard(BOARD_ID));
        return this.commentMapper.getCommentByBoard(BOARD_ID);
    }

    @Override       //댓글 추가(부모)
    public void addComment(CommentDTO commentDTO) {
        this.commentMapper.addComment(commentDTO);
    }

    @Override       // 댓글 아이디 가져오기
    public CommentDTO getCommentId(int COMMENT_ID) {
        return this.commentMapper.getCommentId(COMMENT_ID);
    }

    @Override       // 댓글 수정
    public void editComment(CommentDTO commentDTO) {
        this.commentMapper.editComment(commentDTO);
    }

    @Override       // 댓글 삭제
    public int delComment(int COMMENT_ID) {
        return this.commentMapper.delComment(COMMENT_ID);
    }

    @Override       //보드아이디로 댓글개수 가져오기
    public void getCommentCountByBoardId(int BOARD_ID) {
        this.commentMapper.getCommentCountByBoardId(BOARD_ID);
    }


}
