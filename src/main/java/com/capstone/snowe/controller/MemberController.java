package com.capstone.snowe.controller;

import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.dto.TeacherDTO;
import com.capstone.snowe.dto.TokenDTO;
import com.capstone.snowe.jwt.JwtFilter;
import com.capstone.snowe.jwt.TokenProvider;
import com.capstone.snowe.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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

    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @RequestMapping("/test")
    public String test(@RequestBody String loginId){
        memberService.findMemberByLoginId(loginId);
        return null;
    }

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
    @PostMapping("/apply")
    public MemberDTO applyTeacher(MemberDTO memberDTO,@AuthenticationPrincipal UserDetails user){
        memberService.apply(memberDTO,user);
        return null;
    }


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
                    System.out.println(memberService.getAllTeacher(ridingClass));
                    return memberService.getAllTeacher(ridingClass);
                default:
                    break;

            }
        } else {
            throw new IllegalArgumentException("ridingClass is null");
        }
        return Collections.emptyList();
    }
}