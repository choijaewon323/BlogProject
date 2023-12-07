package com.example.blog.common;

import com.example.blog.dto.AccountRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login");

            return false;
        }

        AccountRequestDto requestDto = (AccountRequestDto) session.getAttribute("success");
        if (requestDto == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        return true;
    }
}
