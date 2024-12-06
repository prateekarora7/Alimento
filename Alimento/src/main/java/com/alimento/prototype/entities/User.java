package com.alimento.prototype.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "user_id")
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNo;

    @OneToMany(mappedBy = "commentId", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
