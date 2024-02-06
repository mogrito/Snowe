package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.LessonDTO;
import com.capstone.snowe.dto.LessonJoinDTO;
import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.mapper.LessonMapper;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonMapper lessonMapper;
    private final MemberMapper memberMapper;

    /*
    * 강사가 강습 등록
    * */
    @Override
    public void lessonInsert(LessonDTO lessonDTO) {
        this.lessonMapper.lessonInsert(lessonDTO);
    }

    /*
     * 강습정보 수정하기
     * */
    @Override
    public void lessonUpdate(LessonDTO lessonDTO, @AuthenticationPrincipal UserDetails user) {
        //토큰값 받아
        MemberDTO memberDTO = memberMapper.findByLoginId(user.getUsername());
        // lessonDTO의 loginId에 토큰에서 받아온 loginId set
        lessonDTO.setLoginId(memberDTO.getLoginId());

        System.out.println("강습 수정의 dto => " + lessonDTO);

        this.lessonMapper.lessonUpdate(lessonDTO);
    }

    /*
     * 강습 삭제하기
     * */
    @Override
    public void lessonDel(LessonDTO lessonDTO, @AuthenticationPrincipal UserDetails user) {
        //토큰값 받아
        MemberDTO memberDTO = memberMapper.findByLoginId(user.getUsername());
        // lessonDTO의 loginId에 토큰에서 받아온 loginId set
        lessonDTO.setLoginId(memberDTO.getLoginId());

        System.out.println("강습 수정의 dto => " + lessonDTO);

        this.lessonMapper.lessonDel(lessonDTO);
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
    * 토큰id랑 lesson의 loginId랑 일치 시 로직진행
    * */
    @Override       // 로그인id찾음
    public int selectLoginIdByLessonId(LessonDTO lessonDTO, @AuthenticationPrincipal UserDetails user) {
        MemberDTO memberDTO = memberMapper.findByLoginId(user.getUsername());

        lessonDTO.setLoginId(memberDTO.getLoginId());

        System.out.println("강습 중복검사의 dto => " + lessonDTO);

        return lessonMapper.selectLoginIdByLessonId(lessonDTO);
    }
}
