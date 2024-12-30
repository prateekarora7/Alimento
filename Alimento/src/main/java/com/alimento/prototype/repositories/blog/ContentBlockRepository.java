package com.alimento.prototype.repositories.blog;

import com.alimento.prototype.entities.blog.BlockType;
import com.alimento.prototype.entities.blog.ContentBlock;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentBlockRepository extends JpaRepository<ContentBlock, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO content_block (block_type, blog_id, block_order, content, url)" +
            "VALUES (:blockType, :blogId, :blockOrder, :content, :url)", nativeQuery = true)
    void saveContentBlock(@Param("blockType") BlockType blockType, @Param("blogId") long blogId, @Param("blockOrder") int blockOrder, @Param("content") String content, @Param("url") String url);



}
