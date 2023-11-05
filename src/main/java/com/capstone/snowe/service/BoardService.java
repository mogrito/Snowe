package com.capstone.snowe.service;

import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.dto.BoardFileDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;


public interface BoardService {

    List<BoardDTO> getBoardList();      //게시글 목록 조회
    List<BoardDTO> oldGetBoardList();      //게시글 목록 조회(오래된순)

    int addBoard(BoardDTO boardDTO,@AuthenticationPrincipal UserDetails user);    //게시글 작성

    void insertBoardFile(BoardFileDTO boardFileDTO);        //파일 첨부
    
    BoardDTO getBoardId(int BOARD_ID);       //게시글 상세조회
    
    int editBoard(BoardDTO boardDTO);   //게시글 수정
    
    void delBoard(int BOARD_ID);              //게시글 삭제

    List<BoardDTO> searchBoard(String searchType, String keyword);        //검색기능

    void increaseRecommendCount(int BOARD_ID);          //추천수 증가
    
    void increaseViewCount(int BOARD_ID);               //조회수 증가

    void increaseCommentCount(int BOARD_ID);            //댓글수 증가 수정

    int addBoardFile(Map<String, Object> params);       //게시글 작성 시 파일저장

    /*int boardCount();        // 게시글 전체 개수(페이징)
    List<BoardDTO> boardPage(int endRow, int startRow); //페이징기능*/


}
