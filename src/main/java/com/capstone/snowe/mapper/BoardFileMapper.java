package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.BoardFileDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardFileMapper {

    /* 파일 저장 */
    void insertBoardFile(BoardFileDTO boardFileDTO);

    //BoardFileDTO getFileByBoard(int boardId);
    List<BoardFileDTO> getFileByBoard(int boardId);


}
