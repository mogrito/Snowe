package com.capstone.snowe.controller;

import com.capstone.snowe.dto.BoardFileDTO;
import com.capstone.snowe.service.BoardFileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
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
    public ResponseEntity<byte[]> getImage(@PathVariable String fileOName){
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
    @GetMapping("/file/{boardId}")
    public ResponseEntity<byte[]> getImage2(@PathVariable int boardId) {
        BoardFileDTO boardFile = boardFileService.getFileByBoard(boardId);

        String path = boardFile.getFilePath();
        String fileName = boardFile.getFileSName();
        File file = new File(path + "\\s_" + fileName);

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

    // 3. 게시글에 해당하는 여러 개의 파일 불러오기
    /*@GetMapping("/file/{boardId}")
    public ResponseEntity<List<byte[]>> getImage3(@PathVariable int boardId) {
        List<BoardFileDTO> boardFileList = boardFileService.getFileByBoard(boardId);
        List<byte[]> images = null;//new ArrayList<>();

        for (BoardFileDTO boardFile : boardFileList) {
            String path = boardFile.getFilePath();
            String fileName = boardFile.getFileSName();
            File file = new File(path + "\\s_" + fileName);

            try {
                *//*byte[] imageBytes = Files.readAllBytes(file.toPath());
                images.add(imageBytes);*//*
                HttpHeaders header = new HttpHeaders();

                header.add("Content-type", Files.probeContentType(file.toPath()));
                byte
                images.add(header);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(images, HttpStatus.OK);
    }*/

}
