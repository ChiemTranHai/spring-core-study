package com.example.entity;


import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Post> posts;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
