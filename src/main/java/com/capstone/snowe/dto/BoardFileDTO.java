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
    private String fileOName;
    private String filePath;
    private long fileSize;
    private String fileType;
    private Date uploadDate;
    private String fileSName;
    private String uuid;

/*    @Builder
    public BoardFileDTO(String fileOName, String fileSName, long fileSize) {
        this.fileOName = fileOName;
        this.fileSName = fileSName;
        this.fileSize = fileSize;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }*/



}
