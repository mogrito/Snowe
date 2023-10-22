package com.capstone.snowe.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String loginId;
    private String password;
    private String name;
    private String birthday;
    private String nickname;
    private String gender;
    private String teacherInfo;
    // 다른 필드들

    // Getter and Setter 메서드
}