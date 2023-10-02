package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.BoardDTO;

import java.util.List;

public interface BoardMapper {


    List<BoardDTO> getBoardList();      // 게시글 목록 가져오기

    int addBoard(BoardDTO boardDTO);    // 게시글 작성

    BoardDTO getBoardId(int BOARD_ID);       // 게시글 자세히보기

    int editBoard(BoardDTO boardDTO);        // 게시글 수정하기

    int delBoard(int BOARD_ID);                  // 게시글 삭제

    List<BoardDTO> searchBoard(String TITLE);         // 검색기능

    int boardCount();       // 게시글 전체 개수(페이징)
    List<BoardDTO> boardPage(int endRow, int startRow);     // 페이징기능
}
