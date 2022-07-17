package com.example.blog.controller;

import com.example.blog.service.PostService;
import com.example.blog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final PostService postService;
    private final ReplyService replyService;

    @GetMapping("/")
    public String getMain(Model model) {
        model.addAttribute("posts", postService.readAll());
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

    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.readOne(id));
        model.addAttribute("replies", replyService.findAll(id));

        return "detail";
    }
}
