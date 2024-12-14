package com.alimento.prototype.dtos.comment;

import lombok.Data;

@Data
public class CommentDTO {

    private String commentContent;

    private int blogId;

    private String userId;

}
