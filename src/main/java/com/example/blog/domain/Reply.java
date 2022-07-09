package com.example.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_ID")
    private Long id;

    @Column(name = "WRITER")
    private String writer;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    public Reply(String writer, String content, Post post) {
        this.writer = writer;
        this.content = content;
        this.post = post;
    }

    public void update(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }
}
