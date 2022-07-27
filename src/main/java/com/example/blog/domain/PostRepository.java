package com.example.blog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitleContains(String title);

    List<Post> findAllByContentContains(String content);
}
