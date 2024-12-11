package com.alimento.prototype.controllers.blog;

import com.alimento.prototype.entities.blogs.ContentBlock;
import com.alimento.prototype.services.blog.ContentBlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content-blocks")
public class ContentBlockController {

    private final ContentBlockService contentBlockService;

    public ContentBlockController(ContentBlockService contentBlockService) {
        this.contentBlockService = contentBlockService;
    }

    @PostMapping("/save")
    public ResponseEntity saveContentBlocks(List<ContentBlock> contentBlocks){
        contentBlockService.saveContentBlocks(contentBlocks);
        return new ResponseEntity(HttpStatus.OK);
    }


}
