package com.alimento.prototype.dtos.blog;

import com.alimento.prototype.entities.blog.BlockType;
import com.alimento.prototype.entities.blog.BlogPost;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentBlockRequest {

    @Enumerated(EnumType.STRING)
    private BlockType blockType;

    private int blockOrder;

    private String content; // Text content or embed code

    private String url; // For images or external embeds

    private String slug;

}
