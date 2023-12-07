package com.example.blog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Integer> {
    Optional<Heart> deleteByPostAndAccount(Post post, Account account);
}
