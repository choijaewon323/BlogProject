package com.example.blog.controller.api;

import com.example.blog.service.PostService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class PostApiController {
    private PostService postService;

    @PostMapping("/api/post")
    public void createPost(@RequestBody String title, @RequestBody String content) {
        postService.createPost(title, content);
    }

    @PutMapping("/api/post/{id}")
    public void updatePost(@RequestBody String title, @RequestBody String content) {

    }

    @DeleteMapping("/api/post/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
