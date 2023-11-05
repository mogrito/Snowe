package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.BoardFileDTO;
import com.capstone.snowe.mapper.BoardFileMapper;
import com.capstone.snowe.service.BoardFileService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class BoardFileServiceImpl implements BoardFileService {

    @Autowired
    private BoardFileMapper boardFileMapper;


    /* 파일 저장 */
    @Override
    public void insertBoardFile(BoardFileDTO boardFileDTO) {
        boardFileMapper.insertBoardFile(boardFileDTO);
    }
}
