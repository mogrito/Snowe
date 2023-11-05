package com.capstone.snowe.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private TokenDTO tokenDTO;
    private MemberDTO memberDTO;

    public ResponseDTO(TokenDTO tokenDTO) {
        this.tokenDTO = tokenDTO;

    }

}
