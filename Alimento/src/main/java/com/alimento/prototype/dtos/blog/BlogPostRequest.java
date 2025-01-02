package com.alimento.prototype.dtos.blog;

import com.alimento.prototype.entities.blog.ContentBlock;
import com.alimento.prototype.entities.tag.Tag;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlogPostRequest {

    // ***This blog post dto is only to save a basic structure for blog first and then we can add the content blocks associated with it***

    private String title;

    private String authorName;

    private String slug;

    private LocalDateTime createdAt;

    private List<ContentBlockRequest> contentBlockList;

}
