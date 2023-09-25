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
        return this.boardMapper.getBoardList();
    }

    @Override       //게시글 작성
    public int addBoard(BoardDTO boardDTO) {

        return this.boardMapper.addBoard(boardDTO);
    }

    @Override       //게시글 상세조회
    public BoardDTO getBoardView(int bno) {
        return this.boardMapper.getBoardView(bno);
    }


}
