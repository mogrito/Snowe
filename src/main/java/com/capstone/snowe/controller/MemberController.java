package com.capstone.snowe.controller;

import com.capstone.snowe.dto.LessonJoinDTO;
import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.dto.TeacherDTO;
import com.capstone.snowe.dto.TokenDTO;
import com.capstone.snowe.jwt.JwtFilter;
import com.capstone.snowe.jwt.TokenProvider;
import com.capstone.snowe.service.BoardFileService;
import com.capstone.snowe.service.LessonService;
import com.capstone.snowe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final LessonService lessonService;
    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final BoardFileService boardFileService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;



    // 토큰 로그인
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody MemberDTO memberDTO){

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberDTO.getLoginId(), memberDTO.getPassword());
        System.out.println(memberDTO.getLoginId() + " " +memberDTO.getPassword());
        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        // response header에 jwt token에 넣어줌
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        // tokenDto를 이용해 response body에도 넣어서 리턴
        return new ResponseEntity<>(new TokenDTO(jwt), httpHeaders, HttpStatus.OK);
    }

    // 강사 신청
    /*@PostMapping("/apply")
    public MemberDTO applyTeacher(@RequestBody TeacherDTO teacherDTO, @AuthenticationPrincipal UserDetails user) {

        String classification = teacherDTO.getClassification();

        switch (classification) {
            case "스키" -> teacherDTO.setClassification("CL01");
            case "보드" -> teacherDTO.setClassification("CL02");
            default -> {
            }
        }

        memberService.apply(teacherDTO, user);
        System.out.println("파일은 없고 DTO는 이래요 ==>> " + teacherDTO);
        return null;
    }*/
    @PostMapping("/apply")
    public ResponseEntity<String> applyTeacher(@RequestBody TeacherDTO teacherDTO, @AuthenticationPrincipal UserDetails user) {

        String classification = teacherDTO.getClassification();

        switch (classification) {
            case "스키" -> teacherDTO.setClassification("CL01");
            case "보드" -> teacherDTO.setClassification("CL02");
            default -> {
            }
        }
        // 강사 요청 테이블 등록
        System.out.println("강사 신청 => " + teacherDTO);
        memberService.apply(teacherDTO, user);

        System.out.println("입력받은 teacherDTO => " + teacherDTO);
        return ResponseEntity.ok("강사 신청 완료");
    }



    /*@PostMapping("/apply")
    public ResponseEntity<List<?>> applyTeacher(@RequestPart(value = "teacher") TeacherDTO teacherDTO, @RequestPart(value="image", required = false) MultipartFile[] files, @AuthenticationPrincipal UserDetails user){

        String classification = teacherDTO.getClassification();

        switch (classification) {
            case "스키" -> teacherDTO.setClassification("CL01");
            case "보드" -> teacherDTO.setClassification("CL02");
            default -> {
            }
        }
        // 강사 요청 테이블 등록
        System.out.println("강사 신청 => " +teacherDTO);
        memberService.apply(teacherDTO, user);

        System.out.println("입력받은 teacherDTO => " + teacherDTO);

        // 저장경로
        String uploadFolder = "C:\\upload\\teacher";

        // 폴더 생성
        File filePath = new File(uploadFolder);

        if (filePath.exists() == false) {
            filePath.mkdirs();
        }


        //이미지 리스트를 담은 객체
        List<BoardFileDTO> list = new ArrayList<>();

        for (MultipartFile multipartFile : files) {

            //이미지 정보 객체
            BoardFileDTO boardFileDTO = new BoardFileDTO();

            // 파일이름 저장
            String fileOName = multipartFile.getOriginalFilename();

            // 파일이름에 UUID
            String uuid = UUID.randomUUID().toString();

            String fileSName = uuid + "_" + fileOName;

            // 파일 위치
            File fileData = new File(filePath, fileSName);

            // 접속한 강사의 아이디 set
            MemberDTO memberDTO = memberService.findMemberByLoginId(user.getUsername());
            boardFileDTO.setLoginId(memberDTO.getLoginId());

            boardFileDTO.setFileOName(fileOName);        // 파일이름(원본)
            boardFileDTO.setFilePath(uploadFolder);     // 파일경로
            boardFileDTO.setFileSize(multipartFile.getSize());  // 파일 사이즈
            boardFileDTO.setFileType(multipartFile.getContentType());   // 파일타입
            boardFileDTO.setFileSName(fileSName);       // 파일이름(저장)
            boardFileDTO.setUuid(uuid);                 // uuid

            try {
                multipartFile.transferTo(fileData);

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("강사 신청 파일 => " + boardFileDTO);
            boardFileService.insertApplyTeacherFile(boardFileDTO);
            list.add(boardFileDTO);

        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }*/

    // ME API
    @GetMapping("/me")
    public UserDetails me(@AuthenticationPrincipal UserDetails userDetails) {
        return memberService.me(userDetails);
    }


    // 회원가입
    @PostMapping("/signup")
    @ResponseBody
    public MemberDTO signup(@RequestBody final MemberDTO params) {
        return memberService.signup(params);
    }

    // 회원 상세정보 조회
    @GetMapping("/members/{loginId}")
    @ResponseBody
    public MemberDTO findMemberByLoginId(@PathVariable final String loginId) {
        return memberService.findMemberByLoginId(loginId);
    }

    // ID 중복체크
    @GetMapping("/member-count")
    @ResponseBody
    public String countMemberByLoginId(@RequestParam final String loginId) {
        System.out.println(loginId);
        int count = memberService.countMemberByLoginId(loginId);

        if (count > 0) {
            return "duplicate"; // 중복된 아이디일 경우
        } else {
            return "not-duplicate"; // 중복되지 않은 아이디일 경우
        }
    }

    // 닉네임 중복체크
    @GetMapping("/member-nickname")
    @ResponseBody
    public String checkNickname(@RequestParam final String nickname) {
        System.out.println(nickname);
        int nickcount = memberService.checkNickname(nickname);

        if (nickcount > 0) {
            return "duplicate"; // 중복된 아이디일 경우
        } else {
            return "not-duplicate"; // 중복되지 않은 아이디일 경우
        }
    }

    // 종목별 강사리스트 불러오기
    @GetMapping("/getTeacherList")
    public List<TeacherDTO> getTeacherList(@RequestParam String ridingClass) {
        if (ridingClass != null) {
            switch (ridingClass) {
                case "Board":
                    System.out.println(ridingClass);
                    System.out.println(memberService.getBoardTeacher(ridingClass));
                    return memberService.getBoardTeacher(ridingClass);
                case "Ski":
                    return memberService.getSkiTeacher(ridingClass);
                case "All":
//                    System.out.println(memberService.getAllTeacher(ridingClass));
                    return memberService.getAllTeacher(ridingClass);
                default:
                    break;

            }
        } else {
            throw new IllegalArgumentException("ridingClass is null");
        }
        return Collections.emptyList();
    }

    /*
     * 해당하는 날짜에 존재하는 강습 정보 리스트 가져오기
     * */
    @GetMapping("/list")
    public ResponseEntity<List<LessonJoinDTO>> ableLesson(@RequestParam("lessonDate") String lessonDate) throws Exception {
        List<LessonJoinDTO> lessonListByDay = lessonService.ableLessonListByDay(lessonDate);
        return ResponseEntity.ok(lessonListByDay);
    }
}