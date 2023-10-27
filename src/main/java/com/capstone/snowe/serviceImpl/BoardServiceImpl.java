package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.mapper.BoardMapper;
import com.capstone.snowe.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;


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

    @Override       //게시글 작성
    public int addBoard(BoardDTO boardDTO) {
        return this.boardMapper.addBoard(boardDTO);
    }

    @Override       //게시글 상세조회
    public BoardDTO getBoardId(int BOARD_ID) {
        return this.boardMapper.getBoardId(BOARD_ID);
    }

    @Override       //게시글 수정
    public void editBoard(BoardDTO boardDTO) {
        this.boardMapper.editBoard(boardDTO);
    }

    @Override       //게시글 삭제
    public int delBoard(int BOARD_ID) {
        return this.boardMapper.delBoard(BOARD_ID);
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


    /*@Override       //게시글 전체 개수(페이징)
    public int boardCount() {
        return this.boardMapper.boardCount();
    }

    @Override
    public List<BoardDTO> boardPage(int endRow, int startRow) {
        return this.boardMapper.boardPage(endRow, startRow);}*/



}
