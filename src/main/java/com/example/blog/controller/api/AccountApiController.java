package com.example.blog.controller.api;

import com.example.blog.dto.AccountRequestDto;
import com.example.blog.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@RestController
public class AccountApiController {
    private final AccountService accountService;

    @PostMapping("/api/login")
    public Boolean login(HttpServletRequest request, @RequestBody AccountRequestDto requestDto) {
        if (accountService.login(requestDto)) {
            HttpSession session = request.getSession();
            session.setAttribute("success", requestDto);
            return true;
        }
        else {
            return false;
        }
    }

    @PostMapping("/api/account")
    public Boolean createAccount(@RequestBody AccountRequestDto requestDto) {
        return accountService.create(requestDto);
    }

    @PutMapping("/api/account")
    public void updatePassword(@RequestBody AccountRequestDto requestDto) {
        accountService.updatePassword(requestDto);
    }

    @DeleteMapping("/api/account")
    public void deleteAccount(@RequestBody AccountRequestDto requestDto) {
        accountService.delete(requestDto);
    }
}
