package com.example.blog.controller;

import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final PostService postService;

    @GetMapping("/")
    public String getMain(Model model) {
        model.addAttribute("posts", postService.readAll());
        return "main";
    }

    @GetMapping("/create")
    public String getCreatePost() {
        return "create";
    }
}
