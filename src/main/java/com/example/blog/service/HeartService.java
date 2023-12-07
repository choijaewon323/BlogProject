package com.example.blog.service;

import com.example.blog.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HeartService {
    private final HeartRepository heartRepository;
    private final AccountRepository accountRepository;
    private final PostRepository postRepository;

    public HeartService(HeartRepository heartRepository, AccountRepository accountRepository, PostRepository postRepository) {
        this.heartRepository = heartRepository;
        this.accountRepository = accountRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public void push(Long postId, String username) throws IllegalArgumentException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException());

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException());

        post.increaseHeart();

        Heart heart = new Heart(post, account);

        heartRepository.save(heart);
    }

    @Transactional
    public void pop(Long postId, String username) throws IllegalArgumentException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException());

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException());

        post.decreaseHeart();

        heartRepository.deleteByPostAndAccount(post, account);
    }
}
