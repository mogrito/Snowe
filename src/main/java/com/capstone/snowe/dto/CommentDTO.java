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
//@Entity
public class CommentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private int commentId;    //댓글id

    @Column(name = "BOARD_ID")
    private int boardId;        //게시글id

    @Column(name = "PARENT_COMMENT_ID")
    private int parentCommentId;    //부모댓글id
    
    @Column(name = "CONTENT")
    private String content;     //댓글내용
    
    @Column(name = "WRITER")
    private String writer;      //댓글 작성자

    @Column(name = "CREATE_DATE")
    private Date createDate;    //댓글 작성일자
    
    @Column(name = "RECOMMEND_COUNT")
    private int recommendCount;     //댓글 추천수
    
    @Column(name = "DEL_YN")
    private String delYn;          //댓글 삭제유무
}
