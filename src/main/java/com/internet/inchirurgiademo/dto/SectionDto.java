package com.internet.inchirurgiademo.dto;

import java.util.LinkedList;
import java.util.List;

public class SectionDto {
    private Long id;
    private String imageFile;
    private Integer position;
    private List<PnTableDto> tableList;


    public SectionDto() {
    }

    public List<PnTableDto> getTableList() {
        return tableList;
    }

    public void setTableList(List<PnTableDto> tableList) {
        this.tableList = tableList;
    }

    public void addTable(PnTableDto table){
        if (this.tableList == null){
            this.tableList = new LinkedList<>();
        }
        this.tableList.add(table);
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
