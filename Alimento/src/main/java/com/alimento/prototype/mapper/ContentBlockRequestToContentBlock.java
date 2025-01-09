package com.alimento.prototype.mapper;

import com.alimento.prototype.dtos.blog.ContentBlockRequest;
import com.alimento.prototype.entities.blog.BlogPost;
import com.alimento.prototype.entities.blog.ContentBlock;

public class ContentBlockRequestToContentBlock {


        public static ContentBlock mapToEntity(ContentBlockRequest request) {
            ContentBlock contentBlock = new ContentBlock();

            contentBlock.setBlockType(request.getBlockType());
            contentBlock.setBlockOrder(request.getBlockOrder());
            contentBlock.setContent(request.getContent());
            contentBlock.setUrl(request.getUrl());

            return contentBlock;
        }
    }
