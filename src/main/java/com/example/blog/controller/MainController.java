package com.example.blog.controller;

import com.example.blog.dto.AccountRequestDto;
import com.example.blog.service.PostService;
import com.example.blog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final PostService postService;
    private final ReplyService replyService;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/login/new")
    public String getNewAccount() {
        return "newAccount";
    }

    @GetMapping("/account/{username}")
    public String getUserDetail(Model model, HttpServletRequest request,
                                @PathVariable String username) {

        HttpSession session = request.getSession(false);
        model.addAttribute("account", (AccountRequestDto) session.getAttribute("success"));

        return "myAccount";
    }

    @GetMapping("/account/update/{username}")
    public String getUpdateAccount(Model model, HttpServletRequest request,
                                   @PathVariable String username) {

        HttpSession session = request.getSession(false);
        model.addAttribute("account", (AccountRequestDto) session.getAttribute("success"));

        return "updateAccount";
    }

    @GetMapping("/")
    public String getMain(Model model, HttpServletRequest request,
                          @RequestParam(required = false) String title, @RequestParam(required = false) String content) {

        HttpSession session = request.getSession(false);
        model.addAttribute("account", (AccountRequestDto) session.getAttribute("success") );

        if (title == null && content == null) {
            model.addAttribute("posts", postService.readAll());
        } else if (title != null && content == null) {
            model.addAttribute("posts", postService.findByTitle(title));
        } else {
            model.addAttribute("posts", postService.findByContent(content));
        }

        return "main";
    }

    @GetMapping("/create")
    public String getCreatePost() {
        return "create";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.readOne(id));

        return "update";
    }
    @GetMapping("/update/detail/{replyID}")
    public String updateDetail(@PathVariable Long replyID, Model model) {

        model.addAttribute("reply", replyService.findOne(replyID));

        return "replyUpdate";
    }

    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable Long id, Model model) {

        model.addAttribute("post", postService.readOne(id));
        model.addAttribute("replies", replyService.findAll(id));

        return "detail";
    }
}
