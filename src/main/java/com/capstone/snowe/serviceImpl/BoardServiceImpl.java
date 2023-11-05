package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.dto.BoardFileDTO;
import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.mapper.BoardFileMapper;
import com.capstone.snowe.mapper.BoardMapper;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final MemberMapper memberMapper;
    private final BoardMapper boardMapper;
    private final BoardFileMapper boardFileMapper;


    @Override       //게시글 목록 조회
    public List<BoardDTO> getBoardList() {
        System.out.println("서비스 : " + boardMapper.getBoardList());
        return this.boardMapper.getBoardList();
    }
    @Override       //게시글 목록 조회(오래된순)
    public List<BoardDTO> oldGetBoardList() {
        System.out.println("서비스 : " + boardMapper.oldGetBoardList());
        return this.boardMapper.oldGetBoardList();
    }

    @Override       //게시글 작성                /* 첨부파일 기능 추가 */
    public int addBoard(BoardDTO boardDTO, @AuthenticationPrincipal UserDetails user) {
        MemberDTO member = memberMapper.findByLoginId(user.getUsername());
        boardDTO.setLoginId(member.getNickname());

        this.boardMapper.addBoard(boardDTO);
        System.out.println("BoardServiceImpl의 addBoard입니다 : " + boardDTO);
        return boardDTO.getBoardId();
    }
    @Override
    public void insertBoardFile(BoardFileDTO boardFileDTO) {
        this.boardFileMapper.insertBoardFile(boardFileDTO);
        System.out.println("BoardServiceImpl의 insertBoardFile입니다 : " + boardFileDTO);
    }

    @Override       //게시글 상세조회
    public BoardDTO getBoardId(int BOARD_ID) {
        return this.boardMapper.getBoardId(BOARD_ID);
    }

    @Override       //게시글 수정
    public int editBoard(BoardDTO boardDTO) {
        return this.boardMapper.editBoard(boardDTO);
    }

    @Override       //게시글 삭제
    public void delBoard(int BOARD_ID) {
        this.boardMapper.delBoard(BOARD_ID);
    }

    @Override       // 검색기능
    public List<BoardDTO> searchBoard(String searchType, String keyword) {
        return this.boardMapper.searchBoard(searchType, keyword);
    }

    @Override       // 추천수증가
    public void increaseRecommendCount(int BOARD_ID) {
        boardMapper.increaseRecommendCount(BOARD_ID);
    }

    @Override       //조회수증가
    public void increaseViewCount(int BOARD_ID) {
        boardMapper.increaseViewCount(BOARD_ID);
    }

    @Override       //댓글수증가 수정
    public void increaseCommentCount(int BOARD_ID) {
        boardMapper.increaseCommentCount(BOARD_ID);
    }

    @Override       //게시글 추가 시 파일저장
    public int addBoardFile(Map<String, Object> params) {
        return this.boardMapper.addBoardFile(params);
    }







    /*@Override       //게시글 전체 개수(페이징)
    public int boardCount() {
        return this.boardMapper.boardCount();
    }

    @Override
    public List<BoardDTO> boardPage(int endRow, int startRow) {
        return this.boardMapper.boardPage(endRow, startRow);}*/





}
