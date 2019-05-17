package com.internet.inchirurgiademo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts_sections")
public class PostSection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "image_file")
    private String imageFileName;

    @Column(name = "position")
    private Integer sectionPosition;

    @OneToMany(mappedBy = "postSection", fetch = FetchType.EAGER)
    private List<PartTable> partTableList = new ArrayList<>();

    public PostSection() {
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public Integer getSectionPosition() {
        return sectionPosition;
    }

    public List<PartTable> getPartTableList() {
        return partTableList;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setSectionPosition(Integer sectionPosition) {
        this.sectionPosition = sectionPosition;
    }

    public void addPartTable(PartTable partTable){
        if (this.partTableList == null) this.partTableList = new ArrayList<>();
        this.partTableList.add(partTable);
        partTable.setPostSection(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostSection)) return false;
        PostSection that = (PostSection) o;
        return getId().equals(that.getId()) &&
                Objects.equals(getPost(), that.getPost()) &&
                Objects.equals(getImageFileName(), that.getImageFileName()) &&
                Objects.equals(getSectionPosition(), that.getSectionPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
