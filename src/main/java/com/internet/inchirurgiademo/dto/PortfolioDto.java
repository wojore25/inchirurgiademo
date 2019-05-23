package com.internet.inchirurgiademo.dto;

import java.util.LinkedList;
import java.util.List;

public class PortfolioDto extends PostDto {

    private List<PostDto> postDtoList;

    public PortfolioDto() {
    }

    @Override
    public String getStatus(){
        return "portfolio";
    }

    public List<PostDto> getPostDtoList() {
        return postDtoList;
    }

    public void addPostDto(PostDto postDto) {
        if (this.postDtoList == null){
            this.postDtoList = new LinkedList<>();
        }
        if (postDto != null) this.postDtoList.add(postDto);
    }

    public void setPostDtoList(List<PostDto> postDtoList) {
        this.postDtoList = postDtoList;
    }
}
