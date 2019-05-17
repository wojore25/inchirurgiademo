package com.internet.inchirurgiademo.services;

import com.internet.inchirurgiademo.dto.PortfolioDto;
import com.internet.inchirurgiademo.dto.PostDto;
import com.internet.inchirurgiademo.dto.ProductDto;
import com.internet.inchirurgiademo.entities.Post;
import com.internet.inchirurgiademo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Function<Post, ProductDto> productDtoFunction;

    @Autowired
    private Function<Post, PortfolioDto> portfolioDtoFunction;

    @Override
    public PostDto findPostDto(Long id) {

        Optional<Post> postOptional = postRepository.findById(id);

        PostDto postDto = null;
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            if (post.getStatus().toLowerCase().equals("product")) {
                postDto = productDtoFunction.apply(post);
            } else if (post.getStatus().toLowerCase().equals("portfolio")){
                postDto = portfolioDtoFunction.apply(post);
            }
        }
        return postDto;

    }
}
