package com.capstone.snowe.mapper;

import com.capstone.snowe.dto.BoardFileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardFileMapper {

    /* 파일 저장 */
    void insertBoardFile(BoardFileDTO boardFileDTO);


}
