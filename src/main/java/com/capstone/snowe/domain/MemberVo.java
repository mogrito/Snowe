package com.capstone.snowe.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class MemberVo {
    private String loginId;
    private String password;
    private String name;
    private String birthday;
    private Date createdDate;
    private Date modifiedDate;
    private String nickname;
    private String gender;
    private String perCode;
    private String teacherInfo;


}
