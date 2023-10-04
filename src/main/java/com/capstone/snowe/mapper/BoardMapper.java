package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.BoardDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardMapper {


    List<BoardDTO> getBoardList();      // 게시글 목록 가져오기
    List<BoardDTO> oldGetBoardList();      // 게시글 목록 가져오기(오래된 순)

    void addBoard(BoardDTO boardDTO);    // 게시글 작성

    BoardDTO getBoardId(int BOARD_ID);       // 게시글 자세히보기

    int editBoard(BoardDTO boardDTO);        // 게시글 수정하기

    void delBoard(int BOARD_ID);                  // 게시글 삭제

    List<BoardDTO> searchBoard(@Param("searchType") String searchType, @Param("keyword") String keyword);         // 검색기능
    
    void increaseRecommendCount(int BOARD_ID);      //추천수증가
    
    void increaseViewCount(int BOARD_ID);           //조회수증가

    void increaseCommentCount(int BOARD_ID);		// 게시글 댓글수 수정

    /*int boardCount();       // 게시글 전체 개수(페이징)
    List<BoardDTO> boardPage(int endRow, int startRow);     // 페이징기능*/
}
