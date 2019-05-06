package com.internet.inchirurgiademo.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "part_numbers_tags")
public class PartTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "part_number")
    private String partNumber;

    public PartTag() {
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartTag)) return false;
        PartTag that = (PartTag) o;
        return getId().equals(that.getId()) &&
                Objects.equals(getPost(), that.getPost()) &&
                Objects.equals(getPartNumber(), that.getPartNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
