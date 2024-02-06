package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.BoardFileDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardFileMapper {

    /* 게시판 파일 저장 */
    void insertBoardFile(BoardFileDTO boardFileDTO);

    /* 강사등록 파일 저장 */
    void insertApplyTeacherFile(BoardFileDTO boardFileDTO);



    //BoardFileDTO getFileByBoard(int boardId);
    List<BoardFileDTO> getFileByBoard(int boardId);




}
