package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private String BOARD_ID;
    private String ID;
    private String BOARD_TITLE;
    private String CONTENT;
    private Date POST_DATE;

}

