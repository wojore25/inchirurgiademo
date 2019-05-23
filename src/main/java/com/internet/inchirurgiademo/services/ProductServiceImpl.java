package com.internet.inchirurgiademo.services;

import com.internet.inchirurgiademo.dto.PortfolioDto;
import com.internet.inchirurgiademo.dto.PostDto;
import com.internet.inchirurgiademo.dto.ProductDto;
import com.internet.inchirurgiademo.entities.Post;
import com.internet.inchirurgiademo.entities.Tag;
import com.internet.inchirurgiademo.repositories.PostRepository;
import com.internet.inchirurgiademo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Function<Post, ProductDto> productDtoFunction;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private Function<Post, PortfolioDto> portfolioDtoFunction;


    @Override
    public ProductDto findProductDto(Long id) {

        Optional<Post> postOptional = postRepository.findById(id);

        ProductDto productDto = null;
        if (postOptional.isPresent()) {
            productDto = productDtoFunction.apply(postOptional.get());
        }
        return productDto;
    }

    @Override
    public List<PostDto> findPostsDtoByTag(String tagName) {
        Optional<Tag> tagOptional = tagRepository.findByName(tagName);
        List<PostDto> postDtoList = new LinkedList<>();
        List<Post> postList = null;
        if (tagOptional.isPresent()) {
            postList = postRepository.findAllByTagListContaining(tagOptional.get());
        }
        if (postList == null) return postDtoList;
        for (Post post : postList) {
            if (post.getStatus().toLowerCase().equals("product")){
                postDtoList.add(productDtoFunction.apply(post));
            } else if (post.getStatus().toLowerCase().equals("portfolio")){
                postDtoList.add(portfolioDtoFunction.apply(post));
            }
        }
        return postDtoList;
    }

}
