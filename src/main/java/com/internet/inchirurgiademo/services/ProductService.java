package com.internet.inchirurgiademo.services;

import com.internet.inchirurgiademo.dto.PostDto;
import com.internet.inchirurgiademo.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto findProductDto(Long id);
    List<PostDto> findPostsDtoByTag(String tagName);
}
