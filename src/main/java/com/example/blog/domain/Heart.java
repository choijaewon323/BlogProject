package com.example.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "HEART")
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HEART_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    public Heart(Post post, Account account) {
        this.post = post;
        this.account = account;
    }
}
