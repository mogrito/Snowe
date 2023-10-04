package com.capstone.snowe.dto;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class BoardDTO {

/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")*/
    private int BOARD_ID;    //게시글 id
    // boardId라고 쓰면 edit은 안되는데 list에서 BOARD_ID값이 나옴

//    @Column(name = "WRITER")
    private String WRITER;          //작성자 id

//    @Column(name = "TITLE")
    private String TITLE; //게시글 제목

//    @Column(name = "CONTENT")
    private String CONTENT;     //게시글 내용

//    @Column(name = "CREATE_DATE")
    private Date createDate;     //작성일자

//    @Column(name = "RECOMMEND_COUNT")
    private int recommendCount;    //추천수
    
//    @Column(name = "VIEW_COUNT")
    private int viewCount;          //조회수
    
//    @Column(name = "DEL_YN")
    private String delYn;           //삭제유무

//    @Column(name = "COMMENT_COUNT")
    private int commentCount;     //댓글수


    /*private String BOARD_ID;    //게시글 id
    private String TITLE; //게시글 제목
    private String CONTENT;     //게시글 내용
    private String WRITER_ID;   //작성자id
    private Date CREATE_DATE;     //게시일자
    private Date MODIFIED_DATE;     //수정일자
    private String DEL_YN;          //삭제유무
    private String NOTICE_YN;          //공지유무
    private Number VIEW_CNT;           //본 횟수*/
}
