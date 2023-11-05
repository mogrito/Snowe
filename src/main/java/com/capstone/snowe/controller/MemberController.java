package com.capstone.snowe.controller;

import com.capstone.snowe.dto.MemberDTO;
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


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @RequestMapping("/test")
    public String test(){
        System.out.println("test");
        return "test";
    }

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

    @GetMapping("/me")
    public UserDetails me(@AuthenticationPrincipal UserDetails userDetails) {
        return memberService.me(userDetails);
    }


    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.do";
    }

    // 회원가입 API
    @PostMapping("/members")
    @ResponseBody
    public String saveMember(@RequestBody final MemberDTO params) {
        return memberService.saveMember(params);
    }

    // 회원 상세정보 조회
    @GetMapping("/members/{loginId}")
    @ResponseBody
    public MemberDTO findMemberByLoginId(@PathVariable final String loginId) {
        return memberService.findMemberByLoginId(loginId);
    }



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

    // 회원 수 카운팅 (ID 중복 체크)
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

}