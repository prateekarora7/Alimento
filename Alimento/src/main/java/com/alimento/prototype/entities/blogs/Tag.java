package com.alimento.prototype.entities.blogs;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {

    private long id;

    private String name;

}
