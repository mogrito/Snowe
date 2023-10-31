package com.capstone.snowe.dto;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class BoardDTO {


    private int boardId;    //게시글 id

    private String writer;          //작성자 id


    private String title; //게시글 제목


    private String content;     //게시글 내용


    private Date createDate;     //작성일자


    private int recommendCount;    //추천수
    

    private int viewCount;          //조회수
    

    private String delYn;           //삭제유무

    private int commentCount;     //댓글수


}
