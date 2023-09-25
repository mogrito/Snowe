package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.BoardDTO;

import java.util.List;

public interface BoardMapper {

    List<BoardDTO> getBoardList();

    int addBoard(BoardDTO boardDTO);

}
