package com.alimento.prototype.entities.user;

import com.alimento.prototype.entities.comment.Comment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNo;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("user-comments")
    private List<Comment> comments;
}
