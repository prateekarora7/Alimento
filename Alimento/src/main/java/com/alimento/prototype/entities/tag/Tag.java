package com.alimento.prototype.entities.tag;

import com.alimento.prototype.entities.blog.BlogPost;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag", indexes = { @Index(name = "idx_tag_name", columnList = "tag")
})
public class Tag {

    @Id
    @Column(name = "tag_id")
    private long tagId;

    @Column(name = "tag", unique = true)
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private Set<BlogPost> blogs = new HashSet<>();

}
