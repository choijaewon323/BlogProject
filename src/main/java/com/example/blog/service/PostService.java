package com.example.blog.service;

import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PostService {
    private PostRepository postRepository;

    @Transactional
    public void createPost(String title, String content) {
        Post newPost = new Post(title, content);
        postRepository.save(newPost);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
