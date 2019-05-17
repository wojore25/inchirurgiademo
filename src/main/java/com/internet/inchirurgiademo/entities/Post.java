package com.internet.inchirurgiademo.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Column(name = "image")
    private String image;


    @OneToMany(mappedBy = "childPost", fetch = FetchType.LAZY,
                cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    private List<PostRelation> childPostRelationList = new LinkedList<>();

    @OneToMany(mappedBy = "parentPost", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    private List<PostRelation> parentPostRelationList = new LinkedList<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<PostSection> postSectionList = new LinkedList<>();


    @ManyToMany(mappedBy = "postList",fetch = FetchType.LAZY,
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Tag> tagList = new LinkedList<>();

    @OneToMany( mappedBy = "post",
                fetch = FetchType.LAZY,
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<PartTag> partTagList = new LinkedList<>();



    public Post() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }





    public List<PostSection> getPostSectionList() {
        return postSectionList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public List<PartTag> getPartTagList() {
        return partTagList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addPostSection(PostSection postSection) {
        if (this.postSectionList == null) this.postSectionList = new ArrayList<>();
        this.postSectionList.add(postSection);
        postSection.setPost(this);
    }

    public void addPartTag(PartTag partTag){
        if (this.partTagList == null) this.partTagList = new ArrayList<>();
        this.partTagList.add(partTag);
        partTag.setPost(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return getId().equals(post.getId()) &&
                Objects.equals(getTitle(), post.getTitle()) &&
                Objects.equals(getStatus(), post.getStatus()) &&
                Objects.equals(getImage(), post.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
