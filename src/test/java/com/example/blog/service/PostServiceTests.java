package com.example.blog.service;

import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import com.example.blog.domain.Reply;
import com.example.blog.domain.ReplyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostServiceTests {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ReplyRepository replyRepository;

    @AfterEach
    void afterEach() {
        replyRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    void createPostTest() {
        postService.createPost("테스트1", "테스트 내용1");

        // test
        Post findPost = postRepository.findAll().get(0);
        assertThat(findPost.getTitle()).isEqualTo("테스트1");
        assertThat(findPost.getContent()).isEqualTo(("테스트 내용1"));
    }

    @Test
    void updatePostTest() {
        Post newPost = new Post("테스트1", "테스트 내용1");
        postRepository.save(newPost);

        // test
        postService.updatePost("테스트2", "테스트 내용2", newPost.getId());

        assertThat(postRepository.findById(newPost.getId()).orElseThrow(() -> new IllegalArgumentException()).getTitle()).isEqualTo("테스트2");
    }

    @Test
    void deletePostTest() {
        Post newPost = new Post("테스트1", "테스트 내용1");
        postRepository.save(newPost);
        Reply newReply = new Reply("작성자1", "내용2", newPost);
        replyRepository.save(newReply);

        // test
        postService.deletePost(newPost.getId());

        assertThat(postRepository.count()).isEqualTo(0L);
    }
}
