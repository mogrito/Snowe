package com.capstone.snowe.controller;

import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.dto.BoardFileDTO;
import com.capstone.snowe.dto.RecommendDTO;
import com.capstone.snowe.service.BoardService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/* 리액트, 부트 연동 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    /*
     * 게시판 전체 리스트 조회
     * */
    @GetMapping ("/list")
    public List<BoardDTO> list() throws Exception {


        return boardService.getBoardList();

    }
    /*
     *
     * 게시판 전체 리스트 조회 (오래된 순)
     *
     * */
    @GetMapping ("/oldlist")
    public List<BoardDTO> oldSortlist() throws Exception {

        return boardService.oldGetBoardList();
    }

    /*
    * 핫게시물
    * */
    @GetMapping("/hot-List")
    public List<BoardDTO> hotBoardList() throws Exception{
        return boardService.hotBoardByRecommend();
    }


    /*
     *
     * 게시글 작성 API
     * 첨부파일도 같이 INSERT
     *
     * */

    //    String uploadPath = "C:\\picture\\";

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public ResponseEntity<List<?>> add(@RequestPart(value = "board") BoardDTO boardDTO, @RequestPart(value="image", required = false)MultipartFile[] files, @AuthenticationPrincipal UserDetails user) throws Exception {
        logger.info(String.valueOf(boardDTO));

        int boardId = boardService.addBoard(boardDTO, user);

        // 이미지 파일이 없을 시,
        if (files == null || files.length == 0) {
            logger.info("파일이 없어요 ");
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }

        logger.info("파일이 있어요 " + files);


        //이미지 파일 체크
//        for (MultipartFile multipartFile : files) {
//            File checkFile = new File(multipartFile.getOriginalFilename());
//            //String type = null;
//
//            try {
//                type = Files.probeContentType(checkFile.toPath());
//                logger.info("MIME TYPE : " + type);
//                if (!type.startsWith("image")) {
//                    List<BoardFileDTO> list = null;
//                    return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            /*if (!type.startsWith("image")) {
//                List<BoardFileDTO> list = null;
//                return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
//            }*/
//        }

        //파일 저장 경로 선언
        String uploadFolder = "C:\\upload";

        //날짜 폴더 경로
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        String str = sdf.format(date);      // str = '2023-10-30'문자열

        String datePath = str.replace("-", File.separator); // '-'를 '\'로 변경

        //폴더 생성
        File filePath = new File(uploadFolder, datePath);

        if (filePath.exists() == false) {
            filePath.mkdirs();
        }

        //이미지의 정보를 담는 객체
        List<BoardFileDTO> list = new ArrayList<>();

        for (MultipartFile multipartFile : files) {
            //이미지 정보 객체
            BoardFileDTO boardFileDTO = new BoardFileDTO();

            //파일이름
            String fileOName = multipartFile.getOriginalFilename();


            boardFileDTO.setBoardId(boardId);
            boardFileDTO.setFileOName(fileOName);
            boardFileDTO.setFilePath(String.valueOf(filePath));
            boardFileDTO.setFileSize(multipartFile.getSize());
            boardFileDTO.setFileType(multipartFile.getContentType());

            logger.info("타입 => " + multipartFile.getContentType());


            //파일 이름에 UUID적용
            String uuid = UUID.randomUUID().toString();
            boardFileDTO.setUuid(uuid);

            fileOName = uuid + "_" + fileOName;
            boardFileDTO.setFileSName(fileOName);

            //파일 위치, 파일 이름을 합친 File 객체
            File fileSName = new File(filePath, fileOName);

            //파일 저장
            try {
                //라이브러리 사용
                File thumbnailFile = new File(filePath, "s_" + fileOName);

                multipartFile.transferTo(fileSName);

                BufferedImage bo_image = ImageIO.read(fileSName);

                //비율
                double ratio = 3;
                int width = (int) (bo_image.getWidth() / ratio);
                int height = (int) (bo_image.getHeight() / ratio);

                Thumbnails.of(fileSName)
                        .size(width, height)
                        .toFile(thumbnailFile);

            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("파일이름 : " + multipartFile.getOriginalFilename());
            logger.info("파일타입 : " + multipartFile.getContentType());
            logger.info("파일크기 : " + multipartFile.getSize());
            logger.info("====================저장될 값======================");
            logger.info("파일에 해당하는 게시글 번호 : " + boardDTO.getBoardId());
            logger.info("저장될 SName이름 : " + boardFileDTO.getFileSName());
            logger.info("저장될 OName이름 : " + boardFileDTO.getFileOName());
            logger.info("uuid : " + boardFileDTO.getUuid());
            logger.info("저장될 경로 : " + boardFileDTO.getFilePath());
            logger.info("파일 사이즈 : " + boardFileDTO.getFileSize());
            logger.info("파일 타입 : " + boardFileDTO.getFileType());


            boardService.insertBoardFile(boardFileDTO);
            list.add(boardFileDTO);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /*
     *
     * 게시글 자세히보기 API
     * 조회수 증가까지 함께
     *
     * */
    // @RequestParam int BOARD_ID
    @GetMapping("/view/{boardId}")
    public ResponseEntity<BoardDTO> getBoardView(@PathVariable int boardId) throws Exception {
        //클릭 시 조회수 증가
        boardService.increaseViewCount(boardId);

        // boardDTO에 boardId set
//        boardDTO.setBoardId(boardId);

        //해당 게시글 보기
        BoardDTO board = boardService.getBoardId(boardId);

        return ResponseEntity.ok(board);
    }

    /*
    * 본인 게시글인지 확인하기 위한 토큰값 검출
    * 게시글 수정, 삭제 검사
    * */
    @GetMapping("/view/token-check")
    public UserDetails tokenCheckByBoard(@AuthenticationPrincipal UserDetails userDetails) throws Exception {
        return boardService.tokenCheckByBoard(userDetails);
    }

    /*
     *
     * 게시글 수정 API
     *
     * */
    @PutMapping("/edit/{boardId}")
    public ResponseEntity<String> editPage(@PathVariable int boardId, @RequestBody BoardDTO boardDTO, @AuthenticationPrincipal UserDetails user) throws Exception {

        try {
            // boardDTO에 boardId set
            boardDTO.setBoardId(boardId);

            this.boardService.editBoard(boardDTO, user);
            return ResponseEntity.ok("게시글 수정완료");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    /*
     *
     * 게시글 삭제 API
     * 논리적 삭제로 구현
     *
     * */
    // ("/board/del")
    //@RequestParam(value="BNO", required = false, defaultValue = "0")
    @PutMapping("/del/{boardId}")
    public ResponseEntity<String> del(@PathVariable int boardId,BoardDTO boardDTO, @AuthenticationPrincipal UserDetails user) throws Exception {
        try {
            // boardDTO에 set
            boardDTO.setBoardId(boardId);

            this.boardService.delBoard(boardDTO, user);
            return ResponseEntity.ok("삭제성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /*
     *
     * 제목, 내용, 작성자 기준으로 게시글 검색하기
     *
     * */
    @GetMapping("/search")                        // 클라이언트에서 검색타입을 맞추고 해당 키워드 요청
    public ResponseEntity<List<BoardDTO>> searchBoard(@RequestParam("searchType") String searchType, @RequestParam("keyword") String keyword) {
        List<BoardDTO> searchResult = this.boardService.searchBoard(searchType, keyword);
        return ResponseEntity.ok(searchResult);
    }

    /*
    * 게시글 추천
    * <트리거로 board테이블의 recommend_count도 update되도록 설정>
    * */
    @PostMapping("/recommend/{boardId}")
    public ResponseEntity<String> recommendByBoard(@PathVariable int boardId, RecommendDTO recommendDTO, @AuthenticationPrincipal UserDetails user) throws Exception {
        // 가져온 boardId값 할당
        recommendDTO.setBoardId(boardId);
        logger.info("이게뭔데" + recommendDTO);
        // 추천 중복검사
        int recommendCount = boardService.checkRecommendByLoginId(recommendDTO, user);

        // 추천을 이미 했으면,
        if (recommendCount == 1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("추천은 한 번만!");
        }

        // 동일한 loginId로 추천을 하지 않았다면, 추천테이블에 insert
        this.boardService.recommendByBoard(recommendDTO, user);

        return ResponseEntity.ok("해당 아이디로 " + boardId + "게시글에 추천을 완료했습니다.");
    }


}