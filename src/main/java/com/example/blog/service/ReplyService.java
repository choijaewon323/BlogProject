package com.example.blog.service;

import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import com.example.blog.domain.Reply;
import com.example.blog.domain.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createReply(String writer, String content, Long postID) {
        Post findPost = postRepository.findById(postID).orElseThrow(() -> new IllegalArgumentException());
        Reply newReply = new Reply(writer, content, findPost);
        replyRepository.save(newReply);
    }

    @Transactional
    public void updateReply(String writer, String content, Long replyID) {
        Reply findReply = replyRepository.findById(replyID).orElseThrow(() -> new IllegalArgumentException());
        findReply.update(writer, content);
    }

    @Transactional
    public void deleteReply(Long replyID) {
        replyRepository.deleteById(replyID);
    }
}
