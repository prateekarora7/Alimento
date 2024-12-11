package com.alimento.prototype.services.impl.blog;

import com.alimento.prototype.entities.blogs.ContentBlock;
import com.alimento.prototype.repositories.blog.ContentBlockRepository;
import com.alimento.prototype.services.blog.ContentBlockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentBlockServiceImpl implements ContentBlockService {

    private final ContentBlockRepository contentBlockRepository;

    public ContentBlockServiceImpl(ContentBlockRepository contentBlockRepository) {
        this.contentBlockRepository = contentBlockRepository;
    }

    @Override
    public void saveContentBlocks(List<ContentBlock> contentBlocks) {

        // This loop is setting block order. Blocks will be arranged according to this order when fetched by frontend
        for (int i = 0; i < contentBlocks.size(); i++) {
            contentBlocks.get(i).setBlockOrder(i+1);

            contentBlockRepository.saveContentBlock(contentBlocks.get(i).getBlockType(), contentBlocks.get(i).getBlockOrder(),
                                                        contentBlocks.get(i).getContent(), contentBlocks.get(i).getUrl());

        }
    }

}
