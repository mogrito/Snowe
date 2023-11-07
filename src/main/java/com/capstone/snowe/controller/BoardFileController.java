package com.capstone.snowe.controller;

import com.capstone.snowe.dto.BoardFileDTO;
import com.capstone.snowe.service.BoardFileService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardFileController {

    private static final Logger logger = LoggerFactory.getLogger(BoardFileController.class);
    private final BoardFileService boardFileService;

    /*
     * 이미지 불러오기
     * */
    // 1. 사진만 불러오는 기능
    @GetMapping("/display/{fileOName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileOName) {
        logger.info("getImage().........." + fileOName);

        File file = new File("C:\\upload\\2023\\11\\06\\" + fileOName);

        ResponseEntity<byte[]> result = null;

        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Content-type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 2. 게시글의 사진 가져옴. but, 여러 개의 사진은 못 가져옴
    /*@GetMapping("/file")
    public ResponseEntity<byte[]> getImage2(@RequestParam int boardId) {
        List<BoardFileDTO> boardFiles = boardFileService.getFileByBoard(boardId);

        // 값이 없을 때,
        if (boardFiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (int i=0; i<boardFiles.toArray().length; i++) {
            BoardFileDTO boardFile = boardFiles.get(i);

            logger.info("이것은 포문이여 " + boardFile);
        }
        // 첫 번째 사진만 받아오기
        BoardFileDTO boardFile = boardFiles.get(0);

        String path = boardFile.getFilePath();
        String fileName = boardFile.getFileSName();
        File file = new File(path + "\\s_" + fileName);

        logger.info("파일의 경로 : " + path);
        logger.info("파일의 이름 : " + fileName);

        ResponseEntity<byte[]> result = null;
        try {
            HttpHeaders header = new HttpHeaders();

            header.add("Content-type", Files.probeContentType(file.toPath()));
            //result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }*/

    // 3. 게시글에 해당하는 여러 개의 파일 불러오기
    @GetMapping("/file")
    public List<ResponseEntity<byte[]>> getImage2(@RequestParam int boardId) {

        List<BoardFileDTO> boardFiles = boardFileService.getFileByBoard(boardId);
        List<ResponseEntity<byte[]>> results = new ArrayList<>();

        for (BoardFileDTO boardFile : boardFiles) {
            String path = boardFile.getFilePath();
            String fileName = boardFile.getFileSName();
            File file = new File(path + "\\s_" + fileName);

            logger.info("파일의 경로 : " + path);
            logger.info("파일의 이름 : " + fileName);
            logger.info("이것은 boardFiles : " + boardFiles);
            logger.info("이건 boardFile : " + boardFile);
            logger.info("file이 어떤건지 확인 : " + file);

            try {
                // 해당 경로에 있는 파일(이미지)의 type가져옴
                HttpHeaders header = new HttpHeaders();
                // Midia의 타입을 지정
                //header.setContentType(MediaType.IMAGE_JPEG);

                //MIME타입 자동 감지 후 add
                header.add("Content-type", Files.probeContentType(file.toPath()));

                logger.info("헤더정보 : " + header + "," + file.toPath());
                logger.info("===================================================");

                //바이트 배열로 읽어옴
                byte[] fileBytes = FileCopyUtils.copyToByteArray(file);

                //result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
                ResponseEntity<byte[]> result = new ResponseEntity<>(fileBytes, header, HttpStatus.OK);

                results.add(result);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }// for문

        return results;
    }

    // 리스트로 안 받아옴
    /*@GetMapping("/file")
    public ResponseEntity<Resource> getImage2(@RequestParam int boardId) {
        BoardFileDTO boardFile = boardFileService.getFileByBoard(boardId);
        String path = boardFile.getFilePath();
        String fileName = boardFile.getFileSName();

        String type = boardFile.getFileType();

        Resource imageResource = new ClassPathResource(path + "/s_" + fileName);
    }*/

}
