package com.example.blog.controller.api;

import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> input = new HashMap<>();
        input.put("title", "제목1");
        input.put("content", "내용1");

        this.mockMvc.perform(post("/api/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updatePostTest() throws Exception {
        Post newPost = new Post("제목1", "내용1");
        postRepository.save(newPost);
        Map<String, String> input = new HashMap<>();
        input.put("title", "제목2");
        input.put("content", "내용2");
        String url = "/api/post/" + newPost.getId();

        this.mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
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
