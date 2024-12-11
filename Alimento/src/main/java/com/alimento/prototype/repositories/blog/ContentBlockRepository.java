package com.alimento.prototype.repositories.blog;

import com.alimento.prototype.entities.blogs.BlockType;
import com.alimento.prototype.entities.blogs.ContentBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentBlockRepository extends JpaRepository<ContentBlock, Long> {

    @Query(value = "INSERT INTO content_block (block_type, block_order, content, url)" +
            "VALUES (:blockType, :blockOrder, :content, :url)")
    void saveContentBlock(@Param("blockType") BlockType blockType, @Param("blockOrder") int blockOrder, @Param("content") String content, @Param("url") String url);

}
