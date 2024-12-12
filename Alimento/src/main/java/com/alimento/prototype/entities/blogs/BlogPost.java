package com.alimento.prototype.entities.blogs;

import com.alimento.prototype.entities.Comment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "blog_post")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private long blogId;

    @NonNull
    @Column(name = "title" ,nullable = false)
    private String title;

    @Column(name = "author_name")
    private String authorName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "blog_id")
    private List<ContentBlock> blocks;

    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "tags")
    private String tags;

}
