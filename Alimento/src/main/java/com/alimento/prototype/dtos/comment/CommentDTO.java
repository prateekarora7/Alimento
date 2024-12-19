package com.alimento.prototype.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CommentDTO {

    private String commentContent;

    private int blogId;

    private String username;

}
