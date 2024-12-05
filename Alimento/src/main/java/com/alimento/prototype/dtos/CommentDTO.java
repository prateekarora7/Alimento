package com.alimento.prototype.dtos;

import lombok.Data;

@Data
public class CommentDTO {

    private String commentContent;

    private int blogId;

    private int userId;

}
