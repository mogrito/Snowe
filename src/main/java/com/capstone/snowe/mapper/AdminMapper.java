package com.capstone.snowe.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    /*
     * 관리자가 강사를 추가
     * */
    void addTeacher(String loginId);

    /*
     * 강사가 추가되면 member테이블의 perCode를 수정
     * */
    void perCodeUpdate(String id);
}
