package com.capstone.snowe.config;

import com.capstone.snowe.dto.LoginResponseDto;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        LoginResponseDto member2 = (LoginResponseDto) session.getAttribute("loginMember");
        // 세션ID가 없을때
        if(member2 == null){
            System.out.println("XXXXXXX");
            // react 로 exception 처리
            throw new Exception("로그인 필요"); // 500
        }else{
            System.out.println(member2);

        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}
