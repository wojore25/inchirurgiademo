package com.internet.inchirurgiademo.dto;

import java.util.LinkedList;
import java.util.List;

public class ProductDto extends PostDto {
    List<SectionDto> sectionDtoList;


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
}


