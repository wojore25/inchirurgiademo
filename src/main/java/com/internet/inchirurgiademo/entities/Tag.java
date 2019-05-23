package com.internet.inchirurgiademo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "term_id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToOne(mappedBy = "tag")
    private TagTaxonomy type;

    @JoinTable(name = "tags_relations",
            joinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "term_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Post> postList = new ArrayList<>();

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TagTaxonomy getType() {
        return type;
    }

    public void setType(TagTaxonomy type) {
        this.type = type;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return getId().equals(tag.getId()) &&
                getName().equals(tag.getName()) &&
                Objects.equals(getPostList(), tag.getPostList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
