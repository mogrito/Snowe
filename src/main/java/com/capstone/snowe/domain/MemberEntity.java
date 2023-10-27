package com.capstone.snowe.domain;

import lombok.Getter;

import java.util.Date;

@Getter
public class MemberEntity {
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