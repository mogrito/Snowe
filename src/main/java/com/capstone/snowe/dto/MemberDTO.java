package com.capstone.snowe.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Data
public class MemberDTO {

    private String loginId;                // 로그인 ID(PK)
    private String password;               // 비밀번호
    private String name;                   // 이름
    private String gender;                 // 성별
    private String birthday;            // 생년월일
    private String nickname;
    private LocalDateTime createdDate;     // 생성일시
    private LocalDateTime modifiedDate;    // 최종 수정일시

    public void encodingPassword(PasswordEncoder passwordEncoder) {
        if (StringUtils.isEmpty(password)) {
            return;
        }

        password = passwordEncoder.encode(password);
        System.out.println("encoder="+password);
    }
    public void clearPassword() {
        this.password = "";
    }
}