package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardFileDTO {

    private int fileId;
    private int boardId;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private String fileType;
    private Date upload_date;

}
