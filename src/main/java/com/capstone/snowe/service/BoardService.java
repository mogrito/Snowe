package com.capstone.snowe.service;

import com.capstone.snowe.dto.BoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BoardService {

    List<BoardDTO> getBoardList();

    int addBoard(BoardDTO boardDTO);
}
