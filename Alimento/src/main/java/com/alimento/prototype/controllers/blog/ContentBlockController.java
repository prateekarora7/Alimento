package com.alimento.prototype.controllers.blog;

import com.alimento.prototype.dtos.blog.ContentBlockRequest;
import com.alimento.prototype.entities.blog.BlockType;
import com.alimento.prototype.entities.blog.ContentBlock;
import com.alimento.prototype.repositories.blog.ContentBlockRepository;
import com.alimento.prototype.services.blog.ContentBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/content-blocks")
public class ContentBlockController {

    @Autowired
    private final ContentBlockService contentBlockService;

    public ContentBlockController(ContentBlockService contentBlockService) {
        this.contentBlockService = contentBlockService;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveContentBlocks(@RequestBody List<ContentBlockRequest> contentBlocks){
        contentBlockService.saveContentBlocks(contentBlocks);
        return new ResponseEntity(HttpStatus.OK);
    }


}
