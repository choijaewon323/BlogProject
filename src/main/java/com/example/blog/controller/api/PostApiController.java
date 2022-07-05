package com.example.blog.controller.api;

import com.example.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class PostApiController {
    private PostService postService;

    @PostMapping("/api/post")
    public void createPost(String title, String content) {
        postService.createPost(title, content);
    }

    @PutMapping("/api/post/{id}")
    public void updatePost(@PathVariable Long id, String title, String content) {
        postService.updatePost(title, content, id);
    }

    @DeleteMapping("/api/post/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
