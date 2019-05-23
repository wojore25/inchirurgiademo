package com.internet.inchirurgiademo.converters;

import com.internet.inchirurgiademo.dto.PostDto;
import com.internet.inchirurgiademo.dto.ProductDto;
import com.internet.inchirurgiademo.dto.TagDto;
import com.internet.inchirurgiademo.entities.Post;
import com.internet.inchirurgiademo.entities.Tag;
import com.internet.inchirurgiademo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class TagsDtoConverter implements Function<Tag, TagDto> {

    @Autowired
    private PostRepository postRepository;

    @Override
    public TagDto apply(Tag tag) {

        if (tag == null) return null;

        TagDto tagDto = new TagDto();

        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
        tagDto.setType(tag.getType().getTagType());
        List<Post> postList = postRepository.findAllByTagListContaining(tag);
        tagDto.setPostDtoList(null);

        return tagDto;
    }
}
