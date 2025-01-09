package com.alimento.prototype.entities.blog;

import com.alimento.prototype.entities.comment.Comment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

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

    @Column(name = "slug", unique = true, nullable = false)
    private String slug;

    @NonNull
    @Column(name = "title" ,nullable = false)
    private String title;

    @Column(name = "author_name")
    private String authorName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "slug", referencedColumnName = "slug")
    @JsonManagedReference("blog-block")
    private List<ContentBlock> blocks;

    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL)
    @JsonManagedReference("blog-comment")
    private List<Comment> comments;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "blog_tags",
            joinColumns = @JoinColumn(name = "slug", referencedColumnName = "slug"),
            inverseJoinColumns = @JoinColumn(name = "tag_name", referencedColumnName = "tag_name"),
            indexes = {
                    @Index(name = "idx_blog_tags_tag_name", columnList = "tag_name"),
                    @Index(name = "idx_blog_tags_slug", columnList = "slug")
            }
    )
    private Set<Tag> tags;

}
