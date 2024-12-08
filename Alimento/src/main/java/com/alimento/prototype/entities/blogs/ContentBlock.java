package com.alimento.prototype.entities.blogs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BlockType type;

    @Column(columnDefinition = "TEXT")
    private String content; // Text content or embed code

    private String url; // For images or external embeds

}

