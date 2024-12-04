package com.alimento.prototype.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "comment", nullable = false)
    private String comment;

    @NonNull
    @Column(name = "blog_id", nullable = false)
    private int blogId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
