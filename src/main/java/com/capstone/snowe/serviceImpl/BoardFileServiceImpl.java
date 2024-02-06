package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.BoardFileDTO;
import com.capstone.snowe.mapper.BoardFileMapper;
import com.capstone.snowe.service.BoardFileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    // 게시글과 함께 파일 첨부
    @Override
    public void insertBoardFile(BoardFileDTO boardFileDTO) {
        this.boardFileMapper.insertBoardFile(boardFileDTO);
        System.out.println("BoardServiceImpl의 insertBoardFile입니다 : " + boardFileDTO);
    }

    /* 강사등록 파일 저장 */
    @Override
    public void insertApplyTeacherFile(BoardFileDTO boardFileDTO) {
        this.boardFileMapper.insertApplyTeacherFile(boardFileDTO);
        System.out.println("apply_teacher의 사진 정보 =>> " + boardFileDTO);
    }

    @Override
    public List<BoardFileDTO> getFileByBoard(int boardId) {

        logger.info("이미지 불러오기~~" + boardId);

        return this.boardFileMapper.getFileByBoard(boardId);
    }
}
