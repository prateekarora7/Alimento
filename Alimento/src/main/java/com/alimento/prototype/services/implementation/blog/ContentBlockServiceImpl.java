package com.alimento.prototype.services.implementation.blog;

import com.alimento.prototype.dtos.blog.ContentBlockRequest;
import com.alimento.prototype.entities.blog.BlockType;
import com.alimento.prototype.entities.blog.BlogPost;
import com.alimento.prototype.entities.blog.ContentBlock;
import com.alimento.prototype.repositories.blog.BlogPostRepository;
import com.alimento.prototype.repositories.blog.ContentBlockRepository;
import com.alimento.prototype.services.blog.ContentBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ContentBlockServiceImpl implements ContentBlockService {

    private final ContentBlockRepository contentBlockRepository;

    public ContentBlockServiceImpl(ContentBlockRepository contentBlockRepository) {
        this.contentBlockRepository = contentBlockRepository;
    }

    @Override
    public void saveContentBlocks(List<ContentBlockRequest> contentBlocks) {

        contentBlocks = verifyBlockOrder(contentBlocks);

        for(ContentBlockRequest block : contentBlocks){
            if(!isValidEnum(BlockType.class, block.getBlockType().name())) throw new RuntimeException("This not a valid BlockType. Block order : " + block.getBlockOrder());// checking if block type is valid enum value or not
            contentBlockRepository.saveContentBlock(block.getBlockType().name(), block.getSlug(), block.getBlockOrder(), block.getContent(), block.getUrl());
        }

    }

    // This is a method to verify if the content blocks array is in order or not
    public List<ContentBlockRequest> verifyBlockOrder(List<ContentBlockRequest> contentBlocks){
        if(contentBlocks == null || contentBlocks.size() == 0) throw new RuntimeException("No content provided");
        for (int i = 0; i < contentBlocks.size(); i++) {
            int order = contentBlocks.get(i).getBlockOrder();
            if(order<i+1) throw new RuntimeException("Block number : " +i +" is not in correct order");
            if(order != i+1) {
                contentBlocks.sort(Comparator.comparingInt(ContentBlockRequest::getBlockOrder)); // if not in order we sort it using comparator
                break;
            }
        }
        // have to add logic for verifying that only block with image will have a url generated for it and is passed as url and embed only comes with a link and nothing else. and except image nothing has a url
        return contentBlocks;
    }

    //This Method verfies of the value of block type is a valid enum value or not
    public boolean isValidEnum(Class<? extends Enum<?>> enumClass, String value) {
        try{
            Enum.valueOf(BlockType.class, value);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

}
