package com.internet.inchirurgiademo.dto;

import java.util.LinkedList;
import java.util.List;

public class ProductDto extends PostDto {
    List<SectionDto> sectionDtoList;
    List<TagDto> tagDtoList;


    @Override
    public String getStatus() {
        return "product";
    }

    public ProductDto() {
    }

    public List<SectionDto> getSectionDtoList() {
        return sectionDtoList;
    }

    public void addSectionDto(SectionDto sectionDto) {
        if (this.sectionDtoList == null){
            this.sectionDtoList = new LinkedList<>();
        }
        if (sectionDto != null) this.sectionDtoList.add(sectionDto);
    }

    public void setSectionDtoList(List<SectionDto> sectionDtoList) {
        this.sectionDtoList = sectionDtoList;
    }

    public List<TagDto> getTagDtoList() {
        return tagDtoList;
    }

    public void setTagDtoList(List<TagDto> tagDtoList) {
        this.tagDtoList = tagDtoList;
    }

    public void addTagDto(TagDto tagDto){
        if (this.getTagDtoList() == null) this.tagDtoList = new LinkedList<>();
        this.tagDtoList.add(tagDto);
    }
}


