package com.capstone.snowe.controller;

import com.capstone.snowe.service.BoardFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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


    /*@PostMapping("/fileUpload")
    public ResponseEntity<Map<String, String>> uploadFile(List<MultipartFile> files) throws Exception {
        files.forEach(file -> {
            System.out.println(file.getContentType());
            System.out.println(file.getOriginalFilename());
        });

        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");

        return ResponseEntity.ok(resultMap);
    }*/

    @PostMapping("/fileUpload")
    public File multipart(MultipartFile multipartFile) throws Exception {
        File file = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return file;
    }



}
