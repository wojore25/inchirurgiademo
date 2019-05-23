package com.internet.inchirurgiademo.entities;

import javax.persistence.*;

@Entity
@Table(name = "term_taxonomy")
public class TagTaxonomy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "term_taxonomy_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "term_id")
    private Tag tag;

    @Column(name = "taxonomy")
    private String tagType;

    @Column(name = "description")
    private String description;

    @Column(name = "parent")
    private Long parent;

    @Column(name = "count")
    private Long count;

    public TagTaxonomy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
