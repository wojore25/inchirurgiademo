package com.internet.inchirurgiademo.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "part_number_tables")
public class PartTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private PostSection postSection;

    @Column(name = "header")
    private String header;

    @Column(name = "table_header")
    private String tableHeader;

    @Column(name = "table_content")
    private String tableContent;

    @Column(name = "table_description")
    private String tableDescription;

    @Column(name = "table_position")
    private  Integer tablePosition;

    public PartTable() {
    }

    public Long getId() {
        return id;
    }

    public PostSection getPostSection() {
        return postSection;
    }

    public String getHeader() {
        return header;
    }

    public String getTableHeader() {
        return tableHeader;
    }

    public String getTableContent() {
        return tableContent;
    }

    public String getTableDescription() {
        return tableDescription;
    }

    public Integer getTablePosition() {
        return tablePosition;
    }

    public void setPostSection(PostSection postSection) {
        this.postSection = postSection;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setTableHeader(String tableHeader) {
        this.tableHeader = tableHeader;
    }

    public void setTableContent(String tableContent) {
        this.tableContent = tableContent;
    }

    public void setTableDescription(String tableDescription) {
        this.tableDescription = tableDescription;
    }

    public void setTablePosition(Integer tablePosition) {
        this.tablePosition = tablePosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartTable)) return false;
        PartTable partTable = (PartTable) o;
        return getId().equals(partTable.getId()) &&
                Objects.equals(getPostSection(), partTable.getPostSection()) &&
                Objects.equals(getHeader(), partTable.getHeader()) &&
                Objects.equals(getTableHeader(), partTable.getTableHeader()) &&
                Objects.equals(getTableContent(), partTable.getTableContent()) &&
                Objects.equals(getTableDescription(), partTable.getTableDescription()) &&
                Objects.equals(getTablePosition(), partTable.getTablePosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
