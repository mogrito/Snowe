package com.capstone.snowe.serviceImpl;

import com.capstone.snowe.dto.BoardDTO;
import com.capstone.snowe.dto.BoardFileDTO;
import com.capstone.snowe.dto.MemberDTO;
import com.capstone.snowe.dto.RecommendDTO;
import com.capstone.snowe.mapper.BoardFileMapper;
import com.capstone.snowe.mapper.BoardMapper;
import com.capstone.snowe.mapper.MemberMapper;
import com.capstone.snowe.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final MemberMapper memberMapper;
    private final BoardMapper boardMapper;
    private final BoardFileMapper boardFileMapper;


    @Override       //게시글 목록 조회
    public List<BoardDTO> getBoardList() {
        System.out.println("서비스 : " + boardMapper.getBoardList());
        return this.boardMapper.getBoardList();
    }
    @Override       //게시글 목록 조회(오래된순)
    public List<BoardDTO> oldGetBoardList() {
        System.out.println("서비스 : " + boardMapper.oldGetBoardList());
        return this.boardMapper.oldGetBoardList();
    }

    @Override       // 핫 게시물
    public List<BoardDTO> hotBoardByRecommend() {
        return this.boardMapper.hotBoardByRecommend();
    }


    @Override       //게시글 작성                /* 첨부파일 기능 추가 */
    public int addBoard(BoardDTO boardDTO, @AuthenticationPrincipal UserDetails user) {
        MemberDTO member = memberMapper.findByLoginId(user.getUsername());
        boardDTO.setLoginId(member.getNickname());

        System.out.println("유저의 로그인 아이디 => " + member.getLoginId());
        System.out.println("유저의 이름과 닉네임 => " + member.getUsername());
        System.out.println("Received JSON: " + boardDTO);

        this.boardMapper.addBoard(boardDTO);
        System.out.println("BoardServiceImpl의 addBoard입니다 : " + boardDTO);
        return boardDTO.getBoardId();
    }
    // 게시글과 함께 파일 첨부
    @Override
    public void insertBoardFile(BoardFileDTO boardFileDTO) {
        this.boardFileMapper.insertBoardFile(boardFileDTO);
        System.out.println("BoardServiceImpl의 insertBoardFile입니다 : " + boardFileDTO);
    }

    @Override       //게시글 상세조회
    public BoardDTO getBoardId(int boardId) {
//        MemberDTO memberDTO = memberMapper.findByLoginId(user.getUsername());
//
//        //boardDTO에 토큰에서 가져온 loginId set
//        boardDTO.setLoginId(memberDTO.getLoginId());
//
//        System.out.println("저장될 recommendDTO : " + boardDTO);

        return this.boardMapper.getBoardId(boardId);
    }

    @Override       // 본인 게시글인지 확인하기 위한 토큰값 검출
    public UserDetails tokenCheckByBoard(@AuthenticationPrincipal UserDetails user) {
        MemberDTO member = memberMapper.findByLoginId(user.getUsername());
        // 아래 정보를 제외한 정보는 null 처리

        MemberDTO memberInfo = new MemberDTO();
        memberInfo.setNickname(member.getNickname());
        memberInfo.setRole(member.getRole());

        return memberInfo;
    }

    @Override       //게시글 수정
    public void editBoard(BoardDTO boardDTO, @AuthenticationPrincipal UserDetails user) {
        MemberDTO memberDTO = memberMapper.findByLoginId(user.getUsername());

        //boardDTO에 토큰에서 가져온 loginId set
        boardDTO.setLoginId(memberDTO.getLoginId());

        System.out.println("수정할 boardDTO => " + boardDTO);
        this.boardMapper.editBoard(boardDTO);
    }

    @Override       //게시글 삭제
    public void delBoard(BoardDTO boardDTO, @AuthenticationPrincipal UserDetails user) {
        MemberDTO memberDTO = memberMapper.findByLoginId(user.getUsername());

        //boardDTO에 토큰에서 가져온 loginId set
        boardDTO.setLoginId(memberDTO.getLoginId());

        this.boardMapper.delBoard(boardDTO);
    }

    @Override       // 검색기능
    public List<BoardDTO> searchBoard(String searchType, String keyword) {
        return this.boardMapper.searchBoard(searchType, keyword);
    }
    
    @Override       // 게시글 추천
    public void recommendByBoard(RecommendDTO recommendDTO, @AuthenticationPrincipal UserDetails user) {
        MemberDTO memberDTO = memberMapper.findByLoginId(user.getUsername());

        System.out.println("유저" + user.getUsername());
        // recommend테이블의 login_id에 토큰 login_id 지정
        recommendDTO.setLoginId(memberDTO.getLoginId());

        System.out.println("저장될 recommendDTO : " + recommendDTO);

        this.boardMapper.recommendByBoard(recommendDTO);
    }
    @Override       // 추천 중복검사
    public int checkRecommendByLoginId(RecommendDTO recommendDTO, @AuthenticationPrincipal UserDetails user) {
        MemberDTO memberDTO = memberMapper.findByLoginId(user.getUsername());
        // recommend테이블의 login_id에 토큰 login_id 지정
        recommendDTO.setLoginId(memberDTO.getLoginId());

        System.out.println("추천 중복검사 : " + recommendDTO);

        return boardMapper.checkRecommendByLoginId(recommendDTO);
    }



    @Override       //조회수증가
    public void increaseViewCount(int BOARD_ID) {
        boardMapper.increaseViewCount(BOARD_ID);
    }

    @Override       //댓글수증가 수정
    public void increaseCommentCount(int BOARD_ID) {
        boardMapper.increaseCommentCount(BOARD_ID);
    }

    @Override       //게시글 추가 시 파일저장
    public int addBoardFile(Map<String, Object> params) {
        return this.boardMapper.addBoardFile(params);
    }







    /*@Override       //게시글 전체 개수(페이징)
    public int boardCount() {
        return this.boardMapper.boardCount();
    }

    @Override
    public List<BoardDTO> boardPage(int endRow, int startRow) {
        return this.boardMapper.boardPage(endRow, startRow);}*/





}
