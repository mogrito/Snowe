package com.capstone.snowe.controller;

import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.dto.BoardFileDTO;
import com.capstone.snowe.service.BoardFileService;
import jakarta.validation.Valid;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/board")
public class BoardFileController {

    @Autowired
    private BoardFileService boardFileService;
    private static final Logger logger = LoggerFactory.getLogger(BoardFileController.class);

    // 토큰 값 받아와서 토큰의 값 뿌리기
    /*@GetMapping
    public String adkd(@AuthenticationPrincipal User user) {

    }*/

    @GetMapping("/display")
    public ResponseEntity<byte[]> getImage(String fileSName) {
        File file = new File("C:\\upload\\" + fileSName);
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




    @PostMapping(value = "/updateFile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<BoardFileDTO>> uploadImage(MultipartFile[] files) throws Exception {

        /* 이미지 파일 체크 */
        for(MultipartFile multipartFile : files) {
            File checkFile = new File(multipartFile.getOriginalFilename());
            String type = null;

            try {
                type = Files.probeContentType(checkFile.toPath());
                logger.info("MIME TYPE : " + type);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!type.startsWith("image")) {
                List<BoardFileDTO> list = null;
                return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
            }
        }

        /* 파일 저장 경로 선언 */
        String uploadFolder = "C:\\upload";

        /* 날짜 폴더 경로 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        String str = sdf.format(date);      // str = '2023-10-30'문자열

        String datePath = str.replace("-", File.separator); // '-'를 '\'로 변경

        /* 폴더 생성 */
        File filePath = new File(uploadFolder, datePath);

        if (filePath.exists() == false) {
            filePath.mkdirs();
        }

        /* 이미지의 정보를 담는 객체 */
        List<BoardFileDTO> list = new ArrayList<>();

        for (MultipartFile multipartFile : files) {
            /* 이미지 정보 객체 */
            BoardFileDTO boardFileDTO = new BoardFileDTO();

            /* 파일이름 */
            String fileOName = multipartFile.getOriginalFilename();
            boardFileDTO.setFileOName(fileOName);
            boardFileDTO.setFilePath(String.valueOf(filePath));
            boardFileDTO.setFileSize(multipartFile.getSize());
            boardFileDTO.setFileType(multipartFile.getContentType());


            /* 파일 이름에 UUID적용 */
            String uuid = UUID.randomUUID().toString();
            boardFileDTO.setUuid(uuid);

            fileOName = uuid + "_" + fileOName;
            boardFileDTO.setFileSName(fileOName);

            /* 파일 위치, 파일 이름을 합친 File 객체 */
            File fileSName = new File(filePath, fileOName);

            /* 파일 저장 */
            try {
                /* 라이브러리 사용 */
                File thumbnailFile = new File(filePath, "s_" + fileOName);

                multipartFile.transferTo(fileSName);

                BufferedImage bo_image = ImageIO.read(fileSName);

                /* 비율 */
                double ratio = 3;
                int width = (int) (bo_image.getWidth() / ratio);
                int height = (int) (bo_image.getHeight() / ratio);

                Thumbnails.of(fileSName)
                        .size(width, height)
                        .toFile(thumbnailFile);

            } catch (Exception e) {
                e.printStackTrace();
            }

            list.add(boardFileDTO);

            //boardFileService.insertBoardFile();

            logger.info("파일이름 : " + multipartFile.getOriginalFilename());
            logger.info("파일타입 : " + multipartFile.getContentType());
            logger.info("파일크기 : " + multipartFile.getSize());
            logger.info("============================================");
        }

        ResponseEntity<List<BoardFileDTO>> result = new ResponseEntity<List<BoardFileDTO>>(list, HttpStatus.OK);
        return result;
    }



}
/* 썸네일 생성 (ImageIO) : 다른방법 1 */
                /*File thumnailFile = new File(filePath, "s_" + fileOName);

                BufferedImage bo_image = ImageIO.read(fileSName);

                *//* 비율 *//*
                double ratio = 3;
                *//* 넓이 높이 *//*
                int width = (int) (bo_image.getWidth() / ratio);
                int height = (int) (bo_image.getHeight() / ratio);

                *//* BufferedImage객체 생성 및 변수에 대입 (도화지를 만드는 과정) *//*
                BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

                *//* 도화지에 그림을 그릴 수 있게 하는 과정 *//*
                Graphics2D graphic = bt_image.createGraphics();

                *//* 도화지에 그림을 그리는 과정 *//*
 *//* 그려놓고자 하는 이미지, 어느 좌표부터 그릴 것 인지에 대한 x, y값(좌상단 맨 끝), 첫 번째 인자로 작성한 이미지의 넓이와 높이, ImageObserver객체 : 이미지 정보를 전달받아 이미지를 업데이트 (일반적으로 null) *//*
                graphic.drawImage(bo_image, 0, 0, width, height, null);

                *//* 파일로 저장할 이미지(우리가 만든 썸네일 이미지), 어떤 이미지 형식으로 저장할지 String타입으로, 썸네일 이미지가 저장될 경로와 생성한 File객체 *//*
                ImageIO.write(bt_image, "jpg", thumnailFile);
                */