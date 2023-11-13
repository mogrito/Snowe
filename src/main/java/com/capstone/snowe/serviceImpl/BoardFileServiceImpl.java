package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.controller.BoardFileController;
import com.capstone.snowe.dto.BoardFileDTO;
import com.capstone.snowe.mapper.BoardFileMapper;
import com.capstone.snowe.service.BoardFileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardFileServiceImpl implements BoardFileService {

    private final BoardFileMapper boardFileMapper;
    private static final Logger logger = LoggerFactory.getLogger(BoardFileServiceImpl.class);


    /*@Override
    public BoardFileDTO getFileByBoard(int boardId) {

        logger.info("이미지 불러오기~~" + boardId);

        return this.boardFileMapper.getFileByBoard(boardId);
    }*/

    @Override
    public List<BoardFileDTO> getFileByBoard(int boardId) {

        logger.info("이미지 불러오기~~" + boardId);

        return this.boardFileMapper.getFileByBoard(boardId);
    }
}
