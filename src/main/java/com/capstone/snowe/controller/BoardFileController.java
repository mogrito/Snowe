package com.capstone.snowe.controller;

import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.dto.BoardFileDTO;
import com.capstone.snowe.service.BoardFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.List;

@CrossOrigin
@RestController
public class BoardFileController {

    @Autowired
    private BoardFileService boardFileService;
    private static final Logger logger = LoggerFactory.getLogger(BoardFileController.class);

    /*
    *
    * 이미지 파일 업로드
    *
    * *//*
    @PostMapping("/fileUpload")
    public Integer fileUpload(@RequestParam("file")MultipartFile file) throws Exception {
        BoardFileDTO fileDTO = new BoardFileDTO();

        fileDTO.setFileName(file.getOriginalFilename());
        fileDTO.setFileType(file.getContentType());
        return 0;
    }*/


/*    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestBody BoardFileDTO boardFileDTO, List<MultipartFile> files) throws Exception {



        files.forEach(file -> {
            System.out.println(file.getContentType());
            System.out.println(file.getOriginalFilename());
        });

        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");

        return ResponseEntity.ok(resultMap);
    }*/
String uploadPath = "C:\\picture\\";
    @PostMapping("/fileUpload")
    public ResponseEntity<String> add(@RequestBody BoardDTO boardDTO, MultipartHttpServletRequest mrequest) throws Exception {

        try {

            BoardFileDTO file = new BoardFileDTO();
            List<MultipartFile> fileList = mrequest.getFiles("file");

            for (MultipartFile mf : fileList) {
                if (!mf.isEmpty()) {
                    String fileName = mf.getOriginalFilename();
                    long fileSize = mf.getSize();

                    file.setBoardId(boardDTO.getBoardId());
                    file.setFileName(fileName);
                    file.setFileSize(fileSize);

                    boardFileService.insertBoardFile(file);//테이블에 파일저장

                    String saveFile = uploadPath + fileName;    //디스크에 파일저장

                    try {
                        mf.transferTo(new File(saveFile));
                    }
                    catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
            }




            return ResponseEntity.ok("작성완료");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



}
