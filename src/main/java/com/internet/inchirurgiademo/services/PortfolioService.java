package com.internet.inchirurgiademo.services;


import com.internet.inchirurgiademo.dto.PortfolioDto;
import com.internet.inchirurgiademo.dto.PostDto;

import java.util.List;

public interface PortfolioService {

    List<PostDto> findChildPortfolios(Long id);
    PortfolioDto findPortfolioDto(Long id);
    List<PostDto> findPostDtoWithTag(String tagName);
    List<PostDto> findSearchedPosts(String search);
}
