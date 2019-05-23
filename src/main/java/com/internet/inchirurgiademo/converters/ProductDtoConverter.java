package com.internet.inchirurgiademo.converters;

import com.internet.inchirurgiademo.dto.ProductDto;
import com.internet.inchirurgiademo.dto.SectionDto;
import com.internet.inchirurgiademo.entities.Post;
import com.internet.inchirurgiademo.entities.PostSection;
import com.internet.inchirurgiademo.repositories.PostRepository;
import com.internet.inchirurgiademo.repositories.PostSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Component
public class ProductDtoConverter implements Function<Post, ProductDto> {

    private PostRepository postRepository;

    @Autowired
    private PostSectionRepository postSectionRepository;

    @Autowired
    private Function<PostSection, SectionDto> sectionDtoConverter;

    @Override
    public ProductDto apply(Post post) {

        ProductDto productDto = null;

        if (post != null && post.getStatus().toLowerCase().equals("product")) {

            productDto = new ProductDto();

            if (post.getId() != null) productDto.setId(post.getId());
            if (post.getTitle() != null) productDto.setTitle(post.getTitle());
            if (post.getImage() != null) productDto.setImageFile("/images/" + post.getImage());

            List<PostSection> postSectionList = postSectionRepository.findAllByPost(post);

            if (postSectionList != null && postSectionList.size() > 0) {
                SectionDto sectionDto = null;
                List<SectionDto> sectionDtoList = new LinkedList<>();
                for (PostSection postSection : postSectionList) {
                    sectionDto = sectionDtoConverter.apply(postSection);
                    productDto.addSectionDto(sectionDto);
                }
            }
            return productDto;
        }

        return null;
    }
}
