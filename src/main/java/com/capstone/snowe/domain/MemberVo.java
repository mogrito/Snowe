package com.capstone.snowe.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
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

    public MemberVo(String loginId, String password, String name, String birthday, String nickname, String gender, String teacherInfo) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.nickname = nickname;
        this.gender = gender;
        this.teacherInfo = teacherInfo;
    }

}
