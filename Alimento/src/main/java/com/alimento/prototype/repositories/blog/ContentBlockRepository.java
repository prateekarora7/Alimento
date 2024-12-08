package com.alimento.prototype.repositories.blog;

import com.alimento.prototype.entities.blogs.ContentBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentBlockRepository extends JpaRepository<Long, ContentBlock> {
}
