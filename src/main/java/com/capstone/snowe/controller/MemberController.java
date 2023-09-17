package com.capstone.snowe.controller;



import com.capstone.snowe.dto.MemberFormDTO;
import com.capstone.snowe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/members/new")
    public String createMemberForm() {
        return "members/newMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(MemberFormDTO memberFormDTO) {
        Long memberId = memberService.join(memberFormDTO);
        return "home";
    }

}