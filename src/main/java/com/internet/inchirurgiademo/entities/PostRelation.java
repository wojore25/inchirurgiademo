package com.internet.inchirurgiademo.entities;

import javax.persistence.*;

@Entity
@Table(name = "posts_relation")
public class PostRelation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post childPost;


    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Post parentPost;

    @Column(name = "position")
    private Integer position;

    public PostRelation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getChildPost() {
        return childPost;
    }

    public void setChildPost(Post childPost) {
        this.childPost = childPost;
    }

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
