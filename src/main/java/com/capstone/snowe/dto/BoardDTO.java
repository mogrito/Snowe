package com.capstone.snowe.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class BoardDTO {

/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")*/
    private int boardId;    //게시글 id
    // boardId라고 쓰면 edit은 안되는데 list에서 BOARD_ID값이 나옴
    // BOARD_ID라고 쓰면 edit은 되는데 list나 view에서 BOARD_ID값을 가져오지 못 함

//    @Column(name = "WRITER")
    private String loginId;          //작성자 id
    // ADD할때 WRITER, TITLE, CONTENT가 소문자로 정의되어 있으면 자꾸 해당 필드의 GETTER값이 없다고 뜸
    //nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named 'WRITER' in 'class com.capstone.snowe.dto.BoardDTO'
    // 그래서 대문자로 바꾸면 된다.. 이상한

//    @Column(name = "TITLE")
    private String title; //게시글 제목

//    @Column(name = "CONTENT")
    private String content;     //게시글 내용

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
