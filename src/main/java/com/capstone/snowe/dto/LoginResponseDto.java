package com.capstone.snowe.dto;

import com.capstone.snowe.domain.MemberVo;
import lombok.Data;

@Data
public class LoginResponseDto {
    private boolean success;
    private String message;
    private MemberVo member;
    private String password;


    public void clearPassword() {
        this.password = "";
    }
}