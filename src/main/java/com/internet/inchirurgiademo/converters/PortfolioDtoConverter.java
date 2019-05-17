package com.internet.inchirurgiademo.converters;

import com.internet.inchirurgiademo.ApplicationResourcesConfig;
import com.internet.inchirurgiademo.dto.PortfolioDto;
import com.internet.inchirurgiademo.dto.PostDto;
import com.internet.inchirurgiademo.dto.ProductDto;
import com.internet.inchirurgiademo.entities.Post;
import com.internet.inchirurgiademo.entities.PostRelation;
import com.internet.inchirurgiademo.repositories.PostRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class PortfolioDtoConverter implements Function<Post, PortfolioDto> {


    @Autowired
    private PostRelationRepository postRelationRepository;


    @Override
    public PortfolioDto apply(Post post) {
        PortfolioDto portfolioDto;

        if (post.getStatus().toLowerCase().equals("portfolio")) {
            portfolioDto = new PortfolioDto();
        } else {
            return null;
        }
        portfolioDto.setId(post.getId());
        portfolioDto.setTitle(post.getTitle());
        portfolioDto.setImageFile("/images/" + post.getImage());

        List<PostRelation> postRelationList = postRelationRepository.findAllByParentPostOrderByPositionAsc(post);
        if (postRelationList != null && postRelationList.size() > 0) {

            PostDto postDto = null;
            for (PostRelation postRelation : postRelationList) {

                postDto = postRelation.getChildPost().getStatus().toLowerCase().equals("portfolio") ?
                        new PortfolioDto() : new ProductDto();
                postDto.setId(post.getId());
                postDto.setImageFile("/images/" + post.getImage());
                postDto.setTitle(post.getTitle());
                portfolioDto.addPostDto(postDto);
            }
        }
        return portfolioDto;
    }


}


