package com.capstone.snowe.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegisterRequestDto {
    private String loginId;
    private String password;
    private String name;
    private String birthday;
    private String nickname;
    private String gender;
    private String teacherInfo;

    public void encodingPassword(PasswordEncoder passwordEncoder) {
    }
}