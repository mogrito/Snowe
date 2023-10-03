package com.capstone.snowe.controller;

import com.capstone.snowe.domain.MemberRequest;
import com.capstone.snowe.domain.MemberResponse;
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
    public MemberResponse login(HttpServletRequest request) {

        // 1. 회원 정보 조회
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        System.out.println(loginId);
        System.out.println(password);
        MemberResponse member = memberService.login(loginId, password);

        // 2. 세션에 회원 정보 저장 & 세션 유지 시간 설정
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);
            session.setMaxInactiveInterval(60 * 30);
        }

        return member;
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
    public String saveMember(@RequestBody final MemberRequest params) {
        return memberService.saveMember(params);
    }

    // 회원 상세정보 조회
    @GetMapping("/members/{loginId}")
    @ResponseBody
    public MemberResponse findMemberByLoginId(@PathVariable final String loginId) {
        return memberService.findMemberByLoginId(loginId);
    }

    // 회원 정보 수정
    @PatchMapping("/members/{id}")
    @ResponseBody
    public String updateMember(@PathVariable final Long id, @RequestBody final MemberRequest params) {
        return memberService.updateMember(params);
    }


    // 회원 수 카운팅 (ID 중복 체크)
    @GetMapping("/member-count")
    @ResponseBody
    public int countMemberByLoginId(@RequestParam final String loginId) {
        System.out.println(loginId);
        return memberService.countMemberByLoginId(loginId);
    }

}