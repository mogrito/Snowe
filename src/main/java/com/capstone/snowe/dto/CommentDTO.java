package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private int commentId;    //댓글id

    private int boardId;        //게시글id

    private int parentCommentId;    //부모댓글id
    
    private String content;     //댓글내용
    
    private String loginId;      //댓글 작성자

    private Date createDate;    //댓글 작성일자
    
    private int recommendCount;     //댓글 추천수
    
    private String delYn;          //댓글 삭제유무
}
