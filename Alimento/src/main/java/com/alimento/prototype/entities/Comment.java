package com.alimento.prototype.entities;

import com.alimento.prototype.entities.blogs.BlogPost;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @NonNull
    @Column(name = "comment_content", nullable = false)
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "blogId", nullable = false)
    @JsonBackReference
    private BlogPost blogPost;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "comment_date")
    private LocalDate commentDate;
}
