package com.example.blog.common;

import com.example.blog.dto.AccountRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Deprecated
public class SessionCommon {
    public static Boolean isConfirm(HttpServletRequest request) {
        HttpSession session = request.getSession();
        AccountRequestDto requestDto = (AccountRequestDto) session.getAttribute("success");
        if (requestDto == null) {
            return false;
        }
        else {
            return true;
        }
    }
}
