package com.alimento.prototype.entities.comment;

import com.alimento.prototype.entities.blog.BlogPost;
import com.alimento.prototype.entities.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @NonNull
    @Column(name = "comment_content", nullable = false)
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "blogId")
    @JsonBackReference("blog-comment")
    private BlogPost blogPost;

    @ManyToOne
    @JoinColumn(name = "username")
    @JsonBackReference("user-comments")
    private User user;

    @Column(name = "comment_date")
    private LocalDate commentDate;
}
