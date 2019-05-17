package com.internet.inchirurgiademo.services;

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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Function<Post, ProductDto> productDtoFunction;



    @Override
    public ProductDto findProductDto(Long id) {

        Optional<Post> postOptional = postRepository.findById(id);

        ProductDto productDto = null;
        if (postOptional.isPresent()) {
            productDto = productDtoFunction.apply(postOptional.get());
        }
        return productDto;
    }
}
