package com.capstone.snowe.service;

import com.capstone.snowe.dto.BoardDTO;

import java.util.List;


public interface BoardService {

    List<BoardDTO> getBoardList();      //게시글 목록 조회

    int addBoard(BoardDTO boardDTO);    //게시글 작성
    
    BoardDTO getBoardNo(int bno);       //게시글 상세조회
    
    int editBoard(BoardDTO boardDTO);   //게시글 수정
    
    int delBoard(int bno);              //게시글 삭제
}
