package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.LessonDTO;
import com.capstone.snowe.dto.LessonJoinDTO;
import com.capstone.snowe.mapper.LessonMapper;
import com.capstone.snowe.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonMapper lessonMapper;

    /*
    * 강사가 강습 등록
    * */
    @Override
    public void lessonInsert(LessonDTO lessonDTO) {

        System.out.println("lessonDTO는 = " + lessonDTO);

        this.lessonMapper.lessonInsert(lessonDTO);
    }

    /*
     * 강습정보 수정하기
     * */
    @Override
    public void lessonUpdate(LessonDTO lessonDTO) {
        this.lessonMapper.lessonUpdate(lessonDTO);
    }

    /*
     * 강습 삭제하기
     * */
    @Override
    public void lessonDel(String lessonId) {
        this.lessonMapper.lessonDel(lessonId);
    }

    /*
     * 해당하는 날짜에 존재하는 강습 정보 리스트 가져오기
     * */
    @Override
    public List<LessonJoinDTO> ableLessonListByDay(String lessonDate) {
        //System.out.println("선택할 날짜 : " + lessonDate);
        //System.out.println("강습목록 : " + lessonMapper.ableLessonListByDay());
        return this.lessonMapper.ableLessonListByDay(lessonDate);
    }
    /*
    * 테스트용 강습 리스트
    * */
    @Override
    public List<LessonJoinDTO> lessonList() {
        return this.lessonMapper.lessonList();
    }
}
