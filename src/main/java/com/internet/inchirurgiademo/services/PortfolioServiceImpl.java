package com.internet.inchirurgiademo.services;

import com.internet.inchirurgiademo.dto.PortfolioDto;
import com.internet.inchirurgiademo.dto.PostDto;
import com.internet.inchirurgiademo.entities.Post;
import com.internet.inchirurgiademo.entities.PostRelation;
import com.internet.inchirurgiademo.repositories.PostRelationRepository;
import com.internet.inchirurgiademo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostRelationRepository postRelationRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private Function<Post, PortfolioDto> portfolioDtoFunction;

    @Override
    public List<PostDto> findChildPortfolios(Long id) {

        List<PostDto> postDtoList = new LinkedList<>();

        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            List<PostRelation> postRelationList = postRelationRepository.findAllByParentPostOrderByPositionAsc(post);
            if (postRelationList != null) {
                PostDto postDto = null;
                for (PostRelation relation : postRelationList) {
                    postDto = postService.findPostDto(relation.getChildPost().getId());
                    if (postDto != null) postDtoList.add(postDto);
                }
            }
        }
        return postDtoList;
    }


    @Override
    public PortfolioDto findPortfolioDto(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        PortfolioDto portfolioDto = null;
        if (postOptional.isPresent()) {
            portfolioDto = portfolioDtoFunction.apply(postOptional.get());
            portfolioDto.setPostDtoList(findChildPortfolios(portfolioDto.getId()));
        }
        return portfolioDto;
    }
}
