package com.capstone.snowe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private String loginId;
    private String teacherId;
    private String name;
    private String resortName;
    private String classification;
    private String introduce;
    private String classLevel;
    private String history;
    private String career;
    private String team;
    private String stat;
    private String applyDate;

}
