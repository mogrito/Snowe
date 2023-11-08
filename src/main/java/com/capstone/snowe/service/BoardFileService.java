package com.capstone.snowe.service;

import com.capstone.snowe.dto.BoardFileDTO;
import java.util.List;

public interface BoardFileService {

    //BoardFileDTO getFileByBoard(int boardId);
    List<BoardFileDTO> getFileByBoard(int boardId);

}
