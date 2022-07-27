package com.example.blog.service;

import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import com.example.blog.domain.Reply;
import com.example.blog.domain.ReplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PostService {
    private PostRepository postRepository;
    private ReplyRepository replyRepository;

    public List<Post> readAll() {
        List<Post> findAll = postRepository.findAll();
        return findAll;
    }

    public Post readOne(Long id) {
        Post findOne = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        return findOne;
    }

    public List<Post> findByTitle(String title) {
        List<Post> results = postRepository.findAllByTitleContains(title);
        return results;
    }

    public List<Post> findByContent(String content) {
        List<Post> results = postRepository.findAllByContentContains(content);
        return results;
    }

    @Transactional
    public void createPost(String title, String content) {
        Post newPost = new Post(title, content);
        postRepository.save(newPost);
    }

    @Transactional
    public void updatePost(String title, String content, Long id) {
        Post findPost = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        findPost.update(title, content);
    }

    @Transactional
    public void deletePost(Long id) {
        Post findPost = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        List<Reply> findReplies = replyRepository.findAllByPost(findPost).orElseThrow(() -> new IllegalArgumentException());

        for (Reply reply : findReplies) {
            replyRepository.deleteById(reply.getId());
        }

        postRepository.deleteById(id);
    }
}
