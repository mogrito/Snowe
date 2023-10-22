package com.capstone.snowe.dto;

import com.capstone.snowe.domain.MemberVo;
import lombok.Data;

@Data
public class LoginResponseDto {
    private boolean success;
    private String message;
    private MemberVo member;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMember(MemberVo member) {
        this.member = member;
    }
}