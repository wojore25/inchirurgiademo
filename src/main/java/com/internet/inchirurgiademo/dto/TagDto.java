package com.internet.inchirurgiademo.dto;

import net.bytebuddy.asm.Advice;

import java.util.LinkedList;
import java.util.List;

public class TagDto {

    private Long id;
    private String name;
    private String type;
    private List<PostDto> postDtoList;

    public TagDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PostDto> getPostDtoList() {
        return postDtoList;
    }

    public void setPostDtoList(List<PostDto> postDtoList) {
        this.postDtoList = postDtoList;
    }

    public void addPostDto(ProductDto productDto){
        if (this.postDtoList == null) this.postDtoList = new LinkedList<>();
        this.postDtoList.add(productDto);
        if (!productDto.getTagDtoList().contains(this)) productDto.addTagDto(this);
    }
}
