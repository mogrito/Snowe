package com.capstone.snowe.controller;

import com.capstone.snowe.dto.MemberRequestDto;
import com.capstone.snowe.dto.MemberResponseDto;
import com.capstone.snowe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@CrossOrigin
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 로그인 API
    @PostMapping("/login")
    @ResponseBody
    public MemberResponseDto login(HttpServletRequest request) {

        // 1. 회원 정보 조회
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        System.out.println(loginId);
        System.out.println(password);
        MemberResponseDto member = memberService.login(loginId, password);


        return memberService.login(loginId, password);
    }

//    // 로그아웃
//    @PostMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/login.do";
//    }

    // 회원가입 API
    @PostMapping("/members")
    @ResponseBody
    public String saveMember(@RequestBody final MemberRequestDto params) {
        return memberService.saveMember(params);
    }

    // 회원 상세정보 조회
    @GetMapping("/members/{loginId}")
    @ResponseBody
    public MemberResponseDto findMemberByLoginId(@PathVariable final String loginId) {
        return memberService.findMemberByLoginId(loginId);
    }

    // 회원 정보 수정
    @PatchMapping("/members/{id}")
    @ResponseBody
    public String updateMember(@PathVariable final Long id, @RequestBody final MemberRequestDto params) {
        return memberService.updateMember(params);
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