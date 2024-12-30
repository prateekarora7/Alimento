package com.alimento.prototype.entities.blog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "content_block")
public class ContentBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "block_type")
    @Enumerated(EnumType.STRING)
    private BlockType blockType;

    @Column(name = "block_order")
    private int blockOrder;

    @Column(columnDefinition = "TEXT")
    private String content; // Text content or embed code

    private String url; // For images or external embeds

}

