package com.example.blog.service;

import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import com.example.blog.domain.Reply;
import com.example.blog.domain.ReplyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReplyServiceTests {
    @Autowired
    ReplyService replyService;
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    PostRepository postRepository;

    @AfterEach
    void afterEach() {
        replyRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    void findAllTest() throws Exception {
        Post post = new Post("테스트1", "테스트 내용1");
        postRepository.save(post);
        Reply reply1 = new Reply("작성자1", "내용1", post);
        Reply reply2 = new Reply("작성자2", "내용2", post);
        replyRepository.save(reply1);
        replyRepository.save(reply2);

        // test
        List<Reply> results = replyService.findAll(post.getId());
        assertThat(results.size()).isEqualTo(2);
        assertThat(results.get(0).getContent()).isEqualTo("내용1");
    }

    @Test
    void findOneTest() throws Exception {
        Reply reply = new Reply("작성자1", "내용1", null);
        replyRepository.save(reply);

        // test
        Reply result = replyService.findOne(reply.getId());
        assertThat(result.getContent()).isEqualTo("내용1");
    }

    @Test
    void createReplyTest() {
        Post newPost = new Post("테스트1", "테스트 내용1");
        postRepository.save(newPost);

        // test
        replyService.createReply("작성자1", "내용1", newPost.getId());

        assertThat(replyRepository.findAll().get(0).getContent()).isEqualTo("내용1");
        assertThat(replyRepository.findAll().get(0).getPost()).isNotNull();
    }

    @Test
    void updateReplyTest() {
        Post newPost = new Post("테스트1", "테스트 내용1");
        postRepository.save(newPost);
        Reply newReply = new Reply("작성자1", "내용1", newPost);
        replyRepository.save(newReply);

        // test
        replyService.updateReply("작성자2", "내용2", newReply.getId());

        Reply testReply = replyRepository.findAll().get(0);
        assertThat(testReply.getWriter()).isEqualTo("작성자2");
        assertThat(testReply.getPost()).isNotNull();
    }

    @Test
    void deleteReplyTest() {
        Post newPost = new Post("테스트1", "테스트 내용1");
        postRepository.save(newPost);
        Reply newReply = new Reply("작성자1", "내용1", newPost);
        replyRepository.save(newReply);

        // test
        replyService.deleteReply(newReply.getId());

        assertThat(replyRepository.count()).isEqualTo(0L);

    }
}
