package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private int BNO;    //게시글 id
    private String USERNAME;          //작성자 id
    private String BOARD_TITLE; //게시글 제목
    private String CONTENT;     //게시글 내용
    private Date POST_DATE;     //게시일자

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
