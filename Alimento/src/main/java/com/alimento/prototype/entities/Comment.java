package com.alimento.prototype.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @Column(name = "blog_id")
    private int blogId;
}
