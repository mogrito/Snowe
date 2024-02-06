package com.capstone.snowe.service;

import com.capstone.snowe.dto.BoardFileDTO;
import java.util.List;

public interface BoardFileService {

    //BoardFileDTO getFileByBoard(int boardId);
    List<BoardFileDTO> getFileByBoard(int boardId);

    void insertBoardFile(BoardFileDTO boardFileDTO);        //파일 첨부

    /* 강사등록 파일 저장 */
    void insertApplyTeacherFile(BoardFileDTO boardFileDTO);

}
