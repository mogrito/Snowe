package com.capstone.snowe.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MemberResponseDto {

    private String loginId;                // 로그인 ID(PK)
    private String password;               // 비밀번호
    private String name;                   // 이름
    private String gender;                 // 성별
    private String birthday;            // 생년월일
    private String nickname;
    @Getter
    private List<String> roles;
    private LocalDateTime createdDate;     // 생성일시
    private LocalDateTime modifiedDate;    // 최종 수정일시

    public void clearPassword() {
        this.password = "";
    }
    public String getUsername() {
        return this.loginId;
    }
}