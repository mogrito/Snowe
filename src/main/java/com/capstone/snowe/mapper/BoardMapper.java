package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.BoardDTO;

import java.util.List;

public interface BoardMapper {


    List<BoardDTO> getBoardList();      // 게시글 목록 가져오기

    int addBoard(BoardDTO boardDTO);    // 게시글 작성

    BoardDTO getBoardView(int bno);       // 게시글 자세히보기
}
