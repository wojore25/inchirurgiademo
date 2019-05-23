package com.internet.inchirurgiademo.temp;

import com.internet.inchirurgiademo.dto.SectionDto;

import java.util.List;

public class Product {
    private List<SectionDto> sectionList;
    private String name;
    private String imageFile;


    public Product() {
    }

    public List<SectionDto> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<SectionDto> sectionList) {
        this.sectionList = sectionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }


}
