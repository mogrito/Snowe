package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.CommentDTO;
import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.mapper.CommentMapper;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final MemberMapper memberMapper;


    @Override       //댓글 목록
    public List<CommentDTO> getCommentByBoard(int BOARD_ID) {

        System.out.println("댓글목록 : " + commentMapper.getCommentByBoard(BOARD_ID));
        return this.commentMapper.getCommentByBoard(BOARD_ID);
    }

    @Override       //댓글 추가(부모)
    public void addComment(CommentDTO commentDTO, @AuthenticationPrincipal UserDetails user) {
        MemberDTO member = memberMapper.findByLoginId(user.getUsername());
        commentDTO.setLoginId(member.getNickname());

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
