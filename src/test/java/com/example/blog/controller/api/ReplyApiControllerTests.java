package com.example.blog.controller.api;

import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import com.example.blog.domain.Reply;
import com.example.blog.domain.ReplyRepository;
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
public class ReplyApiControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
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
    void createReplyTest() throws Exception {
        Post newPost = new Post("제목1", "내용1");
        postRepository.save(newPost);
        Map<String, String> input = new HashMap<>();
        input.put("writer", "작성자1");
        input.put("content", "내용2");
        String url = "/api/reply/" + newPost.getId();

        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateReplyTest() throws Exception {
        Post newPost = new Post("제목1", "내용1");
        postRepository.save(newPost);
        Reply newReply = new Reply("작성자1", "내용2", newPost);
        replyRepository.save(newReply);
        Map<String, String> input = new HashMap<>();
        input.put("writer", "작성자2");
        input.put("content", "내용4");
        String url = "/api/reply/" + newReply.getId();

        this.mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteReplyTest() throws Exception {
        Post newPost = new Post("제목1", "내용1");
        postRepository.save(newPost);
        Reply newReply = new Reply("작성자1", "내용2", newPost);
        replyRepository.save(newReply);
        String url = "/api/reply/" + newReply.getId();

        this.mockMvc.perform(delete(url))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
