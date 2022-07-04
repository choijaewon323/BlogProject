package com.example.blog.service;

import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostServiceTests {
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    public void afterEach() {
        postRepository.deleteAll();
    }

    @Test
    public void createPostTest() {
        postService.createPost("테스트1", "테스트 내용1");

        // test
        Post findPost = postRepository.findAll().get(0);
        assertThat(findPost.getTitle()).isEqualTo("테스트1");
        assertThat(findPost.getContent()).isEqualTo(("테스트 내용1"));
    }
}
