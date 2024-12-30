package com.alimento.prototype.entities.blog;

import com.alimento.prototype.entities.comment.Comment;
import com.alimento.prototype.entities.tag.Tag;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "blog_post", indexes = {
        @Index(name = "idx_blog_tag", columnList = "blogId"),
})
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private long blogId;

    @Column(name = "slug", unique = true)
    private String slug;

    @NonNull
    @Column(name = "title" ,nullable = false)
    private String title;

    @Column(name = "author_name")
    private String authorName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "blog_id")
    private List<ContentBlock> blocks;

    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL)
    @JsonManagedReference("blog-comment")
    private List<Comment> comments;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "blog_tags",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

}
