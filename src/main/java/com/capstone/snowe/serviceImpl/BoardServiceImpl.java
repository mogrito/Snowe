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


    @Override
    public List<BoardDTO> getBoardList() {
        return this.boardMapper.getBoardList();
    }

    @Override
    public int addBoard(BoardDTO boardDTO) {
        return this.boardMapper.addBoard(boardDTO);
    }


}
