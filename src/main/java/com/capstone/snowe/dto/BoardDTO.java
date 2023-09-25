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

}

