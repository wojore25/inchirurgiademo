package com.internet.inchirurgiademo.services;

import com.internet.inchirurgiademo.dto.PortfolioDto;
import com.internet.inchirurgiademo.dto.PostDto;
import com.internet.inchirurgiademo.dto.ProductDto;
import com.internet.inchirurgiademo.entities.PartTag;
import com.internet.inchirurgiademo.entities.Post;
import com.internet.inchirurgiademo.entities.PostRelation;
import com.internet.inchirurgiademo.entities.Tag;
import com.internet.inchirurgiademo.repositories.PartTagRepository;
import com.internet.inchirurgiademo.repositories.PostRelationRepository;
import com.internet.inchirurgiademo.repositories.PostRepository;
import com.internet.inchirurgiademo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private Function<Post, ProductDto> productDtoFunction;

    @Autowired
    private PartTagRepository partTagRepository;

    @Override
    public List<PostDto> findChildPortfolios(Long id) {

        List<PostDto> postDtoList = new LinkedList<>();

        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            List<PostRelation> postRelationList = postRelationRepository.findAllByParentPostOrderByPositionAsc(post);
            if (postRelationList != null) {
                addChildPostsToPostDtoList(postDtoList, postRelationList);
            }
        }
        return postDtoList;
    }

    private void addChildPostsToPostDtoList(List<PostDto> postDtoList, List<PostRelation> postRelationList) {
        PostDto postDto = null;
        for (PostRelation relation : postRelationList) {
            postDto = postService.findPostDto(relation.getChildPost().getId());
            if (postDto != null) postDtoList.add(postDto);
        }
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

    @Override
    public List<PostDto> findPostDtoWithTag(String tagName) {
        List<Post> postList = getPostsWithTagName(tagName);
        List<PostDto> postDtoList = new LinkedList<>();
        if (postList == null) return postDtoList;
        convertPostListToPostDtoList(postList, postDtoList);
        return postDtoList;
    }

    private void convertPostListToPostDtoList(List<Post> postList, List<PostDto> postDtoList) {
        for (Post post : postList) {
            if (post.getStatus().toLowerCase().equals("portfolio")) {
                postDtoList.add(portfolioDtoFunction.apply(post));
            } else if (post.getStatus().toLowerCase().equals("product")) {
                postDtoList.add(productDtoFunction.apply(post));
            }
        }
    }

    private List<Post> getPostsWithTagName(String tagName) {
        Optional<Tag> tagOptional = tagRepository.findByName(tagName);
        List<Post> postList = null;
        if (tagOptional.isPresent()) {
            postList = postRepository.findAllByTagListContaining(tagOptional.get());
        }
        return postList;
    }

    @Override
    public List<PostDto> findSearchedPosts(String search) {

        Set<Post> postSet = searchPosts(search);
        List<PartTag> partTagList = searchPartTags(search);
        addToPostSetPostsFoundByPartTags(postSet, partTagList);
        return convertPostSetToPostDtoList(postSet);
    }

    private void addToPostSetPostsFoundByPartTags(Set<Post> postSet, List<PartTag> partTagList) {
        Optional<Post> postOptional = null;
        if (postSet == null) postSet = new TreeSet<>();
        if (partTagList != null && partTagList.size() > 0) {
            for (PartTag partTag : partTagList) {
                postOptional = postRepository.findFirstByPartTagListContaining(partTag);
                if (postOptional.isPresent()) {
                    postSet.add(postOptional.get());
                }

            }
        }
    }

    private List<PartTag> searchPartTags(String search) {
        return partTagRepository.findAllByPartNumberContaining(search);
    }

    private Set<Post> searchPosts(String search) {
        return postRepository.findAllByTitleContaining(search);
    }

    private List<PostDto> convertPostSetToPostDtoList(Set<Post> postSet) {
        List<PostDto> postDtoList = new LinkedList<>();
        if (postSet != null && postSet.size() > 0) {
            for (Post post : postSet) {
                postDtoList.add(
                        (post.getStatus().equalsIgnoreCase("portfolio")?
                        portfolioDtoFunction.apply(post): productDtoFunction.apply(post)));

            }
        }
        return postDtoList;
    }
}
