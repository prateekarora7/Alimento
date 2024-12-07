package com.alimento.prototype.entities.blogs;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String authorName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "blog_id")
    private List<ContentBlock> blocks;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ElementCollection
    private List<String> tags;

}
