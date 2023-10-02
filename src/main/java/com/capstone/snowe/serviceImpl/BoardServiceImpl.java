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

    @Override       //게시글 작성
    public int addBoard(BoardDTO boardDTO) {
        return this.boardMapper.addBoard(boardDTO);
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
    public int delBoard(int BOARD_ID) {
        return this.boardMapper.delBoard(BOARD_ID);
    }

    @Override       // 검색기능
    public List<BoardDTO> searchBoard(String TITLE) {
        return this.boardMapper.searchBoard(TITLE);
    }

    @Override       //게시글 전체 개수(페이징)
    public int boardCount() {
        return this.boardMapper.boardCount();
    }

    @Override
    public List<BoardDTO> boardPage(int endRow, int startRow) {
        return this.boardMapper.boardPage(endRow, startRow);
    }


}
