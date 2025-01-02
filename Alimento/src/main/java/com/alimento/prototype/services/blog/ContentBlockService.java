package com.alimento.prototype.services.blog;

import com.alimento.prototype.dtos.blog.ContentBlockRequest;
import com.alimento.prototype.entities.blog.ContentBlock;

import java.util.LinkedList;
import java.util.List;

public interface ContentBlockService {

    void saveContentBlocks(List<ContentBlockRequest> contentBlocks);

}
