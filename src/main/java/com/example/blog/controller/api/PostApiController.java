package com.example.blog.controller.api;

import com.example.blog.dto.PostRequestDto;
import com.example.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class PostApiController {
    private PostService postService;

    @PostMapping("/api/post")
    public void createPost(@RequestBody PostRequestDto requestDto) {
        postService.createPost(requestDto.getTitle(), requestDto.getContent());
    }

    @PutMapping("/api/post/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.updatePost(requestDto.getTitle(), requestDto.getContent(), id);
    }

    @DeleteMapping("/api/post/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
