package com.capstone.snowe.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class BoardDTO {


    private int boardId;    //게시글 id

    private String loginId;          //작성자 id

    private String title; //게시글 제목

    private String content;     //게시글 내용

    private Date createDate;     //작성일자

    private int recommendCount;    //추천수

    private int viewCount;          //조회수

    private String delYn;           //삭제유무

    private int commentCount;     //댓글수

    private String category;        // 카테고리

    private List<BoardFileDTO> boardFile;   // DB에 전달하기 위해 생성


}
