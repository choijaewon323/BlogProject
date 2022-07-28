package com.example.blog.controller;

import com.example.blog.common.SessionCommon;
import com.example.blog.service.PostService;
import com.example.blog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/")
    public String getMain(Model model, HttpServletRequest request,
                          @RequestParam(required = false) String title, @RequestParam(required = false) String content) {
        if (!SessionCommon.isConfirm(request)) {
            return "login";
        }

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
    public String getCreatePost(HttpServletRequest request) {
        if (!SessionCommon.isConfirm(request)) {
            return "login";
        }
        return "create";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePost(HttpServletRequest request,
            @PathVariable Long id, Model model) {
        if (!SessionCommon.isConfirm(request)) {
            return "login";
        }

        model.addAttribute("post", postService.readOne(id));

        return "update";
    }
    @GetMapping("/update/detail/{replyID}")
    public String updateDetail(HttpServletRequest request,
            @PathVariable Long replyID, Model model) {
        if (!SessionCommon.isConfirm(request)) {
            return "login";
        }

        model.addAttribute("reply", replyService.findOne(replyID));

        return "replyUpdate";
    }

    @GetMapping("/detail/{id}")
    public String getDetail(HttpServletRequest request,
            @PathVariable Long id, Model model) {
        if (!SessionCommon.isConfirm(request)) {
            return "login";
        }

        model.addAttribute("post", postService.readOne(id));
        model.addAttribute("replies", replyService.findAll(id));

        return "detail";
    }
}
