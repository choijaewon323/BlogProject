package com.example.blog.controller.api;

import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import com.example.blog.dto.PostRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostApiControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    PostRepository postRepository;

    @AfterEach
    void afterEach() {
        postRepository.deleteAll();
    }

    @Test
    void createPostTest() throws Exception {
        PostRequestDto requestDto = new PostRequestDto("제목1", "내용1");

        this.mockMvc.perform(post("/api/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updatePostTest() throws Exception {
        Post newPost = new Post("제목1", "내용1");
        postRepository.save(newPost);
        PostRequestDto requestDto = new PostRequestDto("제목2", "내용2");
        String url = "/api/post/" + newPost.getId();

        this.mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deletePostTest() throws Exception {
        Post newPost = new Post("제목1", "내용1");
        postRepository.save(newPost);
        String url = "/api/post/" + newPost.getId();

        this.mockMvc.perform(delete(url))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
